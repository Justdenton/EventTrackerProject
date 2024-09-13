console.log('script.js loaded');

window.addEventListener('load', function(){
	console.log('DOM loaded');
	init();
});

function init(){
	loadAllExpenses();
	
	//TODO - event listeners, etc.
}

function loadAllExpenses(){
	let xhr = new XMLHttpRequest();
	xhr.open('GET', 'api/expenses');
	xhr.onreadystatechange = function(){
		if (xhr.readyState === xhr.DONE){
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

function displayExpensesList(expensesList){
	let tbody = document.getElementById('expensesListBody');
	tbody.textContent = '';
	
	expensesList.forEach(function(expense){
		
		let tr = document.createElement('tr');
		
		let tdId = document.createElement('td');
		tdId.textContent = expense.id;
		tr.appendChild(tdId);
		
		let tdAmount = document.createElement('td');
		tdId.textContent = expense.amount;
		tr.appendChild(tdAmount);
		
		let tdDescription = document.createElement('td');
		tdId.textContent = expense.description;
		tr.appendChild(tdDescription);
		
		let tdEntered = document.createElement('td');
		tdId.textContent = expense.createTime;
		tr.appendChild(tdEntered);
	});
	
	
}











