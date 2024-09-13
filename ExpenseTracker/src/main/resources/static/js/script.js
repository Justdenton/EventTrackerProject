console.log('script.js loaded');

window.addEventListener('load', function() {
	console.log('DOM loaded');
	init();
});

function init() {
	loadAllExpenses();

	//TODO - event listeners, etc.
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
			}
			else {
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
	// AMOUNT
	let tdAmount = document.createElement('td');
	tdAmount.textContent = expense.amount;
	tr.appendChild(tdAmount);
	// DESCRIPTION
	let tdDescription = document.createElement('td');
	tdDescription.textContent = expense.description;
	tr.appendChild(tdDescription);
	// CREATE DATE
	let tdEntered = document.createElement('td');
	tdEntered.textContent = expense.createTime;
	tr.appendChild(tdEntered);

	tbody.appendChild(tr);
}











