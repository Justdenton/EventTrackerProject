console.log('script.js loaded');

window.addEventListener('load', function() {
	console.log('DOM loaded');
	init();
});

function init() {
	loadAllExpenses();

	// 
}

function loadAllExpenses() {
	let xhr = new XMLHttpRequest();
	xhr.open('GET', 'api/expenses');
	xhr.onreadystatechange = function() {
		if (xhr.readyState === xhr.DONE) {
			if (xhr.status === 200) {
				let expenses = JSON.parse(xhr.responseText);
				console.log(expenses);
				displayExpensesList(expenses);
			} else {
				console.error("Failed to load expenses: " + xhr.status);
			}
		}
	};
	xhr.send();
}

function displayExpensesList(expensesList) {
	let tbody = document.getElementById('expensesListBody');
	tbody.textContent = '';

	expensesList.forEach(function(expense) {
		displaySingleExpense(expense);
	});
}

function displaySingleExpense(expense) {
	let tbody = document.getElementById('expensesListBody');

	let tr = document.createElement('tr');

	// ID
	let tdId = document.createElement('td');
	tdId.textContent = expense.id;
	tr.appendChild(tdId);

	
	// EXAMPLE
	// 	let length = new Number(cave.exploredLengthKm);
	// td.textContent = length.toFixed(2); // 2 decimal digits
	
	// AMOUNT
	let tdAmount = document.createElement('td');
	tdAmount.textContent = expense.amount.toFixed(2);
	tr.appendChild(tdAmount);

	// DESCRIPTION
	let tdDescription = document.createElement('td');
	tdDescription.textContent = expense.description;
	tr.appendChild(tdDescription);

	// EXAMPLE
	// let lastUpdate = new Date(cave.lastUpdate);
	// td.textContent = lastUpdate.toDateString(); 
		// or lastUpdate.toLocaleDateString()
	
	// CREATE DATE
	let tdEntered = document.createElement('td');
	if (expense.createTime) {
		let createDate = new Date(expense.createTime);
		tdEntered.textContent = createDate.toLocaleDateString();
	} else {
		tdEntered.textContent = '-';
	}
	tr.appendChild(tdEntered);

	// UPDATE DATE
	let tdModified = document.createElement('td');
	if (expense.updateTime) {
		let updateDate = new Date(expense.updateTime);
		tdModified.textContent = updateDate.toLocaleDateString();
	} else {
		tdModified.textContent = '-';
	}
	tr.appendChild(tdModified);


	// BUTTONS******************************************
	// UPDATE (Button)
	let tdUpdate = document.createElement('td');
	let updateBtn = document.createElement('button');
	updateBtn.textContent = 'Update';
	updateBtn.addEventListener('click', function() {
		updateExpense(expense.id);
	});
	tdUpdate.appendChild(updateBtn);
	tr.appendChild(tdUpdate);

	// DISABLE/ENABLE (Button)
	let tdDisable = document.createElement('td');
	let disableBtn = document.createElement('button');
	disableBtn.textContent = expense.enabled ? 'Disable' : 'Enable';
	disableBtn.addEventListener('click', function() {
		disableExpense(expense.id, !expense.enabled);
	});
	tdDisable.appendChild(disableBtn);
	tr.appendChild(tdDisable);

	tbody.appendChild(tr);
}





function updateExpense(expenseId) {
	let updatedAmount = prompt("Enter the new amount:");
	let updatedDescription = prompt("Enter the new description:");

	if (updatedAmount && updatedDescription) {
		let updatedExpense = {
			amount: updatedAmount,
			description: updatedDescription
		};

		let xhr = new XMLHttpRequest();
		xhr.open('PUT', 'api/expenses/' + expenseId, true);
		xhr.setRequestHeader('Content-Type', 'application/json');
		xhr.onreadystatechange = function() {
			if (xhr.readyState === xhr.DONE) {
				if (xhr.status === 200) {
					loadAllExpenses(); 
				} else {
					console.error('Error updating expense: ' + xhr.status);
				}
			}
		};
		xhr.send(JSON.stringify(updatedExpense));
	}
}

function disableExpense(expenseId, newStatus) {
	let xhr = new XMLHttpRequest();
	xhr.open('PUT', 'api/expenses/' + expenseId + '/disable', true);
	xhr.setRequestHeader('Content-Type', 'application/json');
	xhr.onreadystatechange = function() {
		if (xhr.readyState === xhr.DONE) {
			if (xhr.status === 200) {
				loadAllExpenses();
			} else {
				console.error('Error disabling/enabling expense: ' + xhr.status);
			}
		}
	};
	xhr.send(JSON.stringify({ enabled: newStatus }));
}

