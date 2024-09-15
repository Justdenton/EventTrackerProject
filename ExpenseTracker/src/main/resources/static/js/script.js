console.log('script.js loaded');

window.addEventListener('load', function() {
	init();
});


let updatingExpenseId = null;

function init() {
	loadAllExpenses();
	loadCategories();
	loadPaymentMethods();
	loadUsers();
	document.getElementById('addExpenseBtn').addEventListener('click', submitNewExpense);
}

function loadAllExpenses() {
	let xhr = new XMLHttpRequest();
	xhr.open('GET', 'api/expenses');
	xhr.onreadystatechange = function() {
		if (xhr.readyState === xhr.DONE && xhr.status === 200) {
			let expenses = JSON.parse(xhr.responseText);
			displayExpensesList(expenses);
		}
	};
	xhr.send();
}

function displayExpensesList(expensesList) {
	let tbody = document.getElementById('expensesListBody');
	tbody.textContent = '';

	expensesList.forEach(function(expense) {
		let tr = document.createElement('tr');

		let tdId = document.createElement('td');
		tdId.textContent = expense.id || 'N/A';
		tr.appendChild(tdId);

		let tdAmount = document.createElement('td');
		tdAmount.textContent = expense.amount ? expense.amount.toFixed(2) : 'N/A';
		tr.appendChild(tdAmount);

		let tdDescription = document.createElement('td');
		tdDescription.textContent = expense.description || 'N/A';
		tr.appendChild(tdDescription);

		let tdCategory = document.createElement('td');
		tdCategory.textContent = expense.category ? expense.category.name : 'N/A';
		tr.appendChild(tdCategory);

		let tdPaymentMethod = document.createElement('td');
		tdPaymentMethod.textContent = expense.paymentMethod ? expense.paymentMethod.name : 'N/A';
		tr.appendChild(tdPaymentMethod);

		let tdEntered = document.createElement('td');
		tdEntered.textContent = expense.createTime ? new Date(expense.createTime).toLocaleDateString() : 'N/A';
		tr.appendChild(tdEntered);

		let tdModified = document.createElement('td');
		tdModified.textContent = expense.updateTime ? new Date(expense.updateTime).toLocaleDateString() : 'N/A';
		tr.appendChild(tdModified);

		let tdUpdate = document.createElement('td');
		let updateBtn = document.createElement('button');
		updateBtn.textContent = 'Update';
		updateBtn.className = 'btn btn-secondary';
		updateBtn.addEventListener('click', function() {
			populateFormWithExpense(expense);
		});
		tdUpdate.appendChild(updateBtn);
		tr.appendChild(tdUpdate);

		let tdDisable = document.createElement('td');
		let disableBtn = document.createElement('button');
		disableBtn.textContent = expense.enabled ? 'Disable' : 'Enable';
		disableBtn.className = 'btn btn-secondary';
		disableBtn.addEventListener('click', function() {
			disableExpense(expense.id, !expense.enabled);
		});
		tdDisable.appendChild(disableBtn);
		tr.appendChild(tdDisable);

		let tdDelete = document.createElement('td');
		let deleteBtn = document.createElement('button');
		deleteBtn.textContent = 'Delete';
		deleteBtn.className = 'btn btn-secondary';
		deleteBtn.addEventListener('click', function() {
			deleteExpense(expense.id);
		});
		tdDelete.appendChild(deleteBtn);
		tr.appendChild(tdDelete);

		tbody.appendChild(tr);
	});
}

function populateFormWithExpense(expense) {
	document.getElementById('amountInput').value = expense.amount;
	document.getElementById('descriptionInput').value = expense.description;
	document.getElementById('categorySelect').value = expense.category ? expense.category.id : '';
	document.getElementById('paymentMethodSelect').value = expense.paymentMethod ? expense.paymentMethod.id : '';
	document.getElementById('userSelect').value = expense.user ? expense.user.id : '';
	updatingExpenseId = expense.id;
	document.getElementById('addExpenseBtn').textContent = 'Update Expense';
}

function submitNewExpense() {
	let amount = parseFloat(document.getElementById('amountInput').value);
	let description = document.getElementById('descriptionInput').value;
	let categoryId = document.getElementById('categorySelect').value;
	let paymentMethodId = document.getElementById('paymentMethodSelect').value;
	let userId = document.getElementById('userSelect').value;

	if (!amount || !description || !categoryId || !paymentMethodId || !userId) {
		alert('Please fill out all fields.');
		return;
	}

	let expenseData = {
		amount: amount,
		description: description,
		category: { id: parseInt(categoryId) },
		paymentMethod: { id: parseInt(paymentMethodId) },
		user: { id: parseInt(userId) }
	};

	let xhr = new XMLHttpRequest();
	if (updatingExpenseId) {
		xhr.open('PUT', `api/expenses/${updatingExpenseId}`);
	} else {
		xhr.open('POST', 'api/expenses');
	}
	xhr.setRequestHeader('Content-Type', 'application/json');
	xhr.onreadystatechange = function() {
		if (xhr.readyState === xhr.DONE) {
			if (xhr.status === 201 || xhr.status === 200) {
				location.reload();
			} else {
				console.error('Error submitting expense: ' + xhr.status);
			}
		}
	};
	xhr.send(JSON.stringify(expenseData));
}

function loadCategories() {
	let selectElement = document.getElementById('categorySelect');
	let xhr = new XMLHttpRequest();
	xhr.open('GET', 'api/categories');
	xhr.onreadystatechange = function() {
		if (xhr.readyState === xhr.DONE && xhr.status === 200) {
			let categories = JSON.parse(xhr.responseText);
			populateDropdown(categories, selectElement);
		}
	};
	xhr.send();
}

function loadPaymentMethods() {
	let selectElement = document.getElementById('paymentMethodSelect');
	let xhr = new XMLHttpRequest();
	xhr.open('GET', 'api/paymentmethods');
	xhr.onreadystatechange = function() {
		if (xhr.readyState === xhr.DONE && xhr.status === 200) {
			let paymentMethods = JSON.parse(xhr.responseText);
			populateDropdown(paymentMethods, selectElement);
		}
	};
	xhr.send();
}

function loadUsers() {
	let selectElement = document.getElementById('userSelect');
	let xhr = new XMLHttpRequest();
	xhr.open('GET', 'api/users');
	xhr.onreadystatechange = function() {
		if (xhr.readyState === xhr.DONE && xhr.status === 200) {
			let users = JSON.parse(xhr.responseText);
			populateDropdown(users, selectElement);
		}
	};
	xhr.send();
}

function populateDropdown(items, selectElement) {
	selectElement.innerHTML = '';
	let defaultOption = document.createElement('option');
	defaultOption.text = 'Select an option';
	defaultOption.value = '';
	selectElement.appendChild(defaultOption);

	items.forEach(function(item) {
		let option = document.createElement('option');
		option.text = item.name || (item.firstName + ' ' + item.lastName);
		option.value = item.id;
		selectElement.appendChild(option);
	});
}

// DISABLE (will update once user session is tracked)
function disableExpense(expenseId, newStatus) {
	let xhr = new XMLHttpRequest();
	xhr.open('PUT', `api/expenses/${expenseId}/disable`);
	xhr.setRequestHeader('Content-Type', 'application/json');
	xhr.onreadystatechange = function() {
		if (xhr.readyState === xhr.DONE && xhr.status === 200) {
			loadAllExpenses();
		}
	};
	xhr.send(JSON.stringify({ enabled: newStatus }));
}

// DELETE
function deleteExpense(expenseId) {
	if (confirm("Are you sure you want to delete this expense?")) {
		let xhr = new XMLHttpRequest();
		xhr.open('DELETE', `api/expenses/${expenseId}`);
		xhr.onreadystatechange = function() {
			if (xhr.readyState === xhr.DONE && xhr.status === 204) {
				loadAllExpenses();
			}
		};
		xhr.send();
	}

}
