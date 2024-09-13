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
			}
		}
	};
	xhr.send();
}

function displayExpensesList(expensesList){
	let tbody = document.getElementsById('expensesListBody');
}

