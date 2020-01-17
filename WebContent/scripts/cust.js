function getMyBank() {
	fetch('http://localhost:2222/p1_reliable_bank/mybank')
    .then((res) => res.json())
    .then((data) => {
        let output = '<h2>My Bank</h2>' + 
        `<table>
        	<tr>
        		<th style="padding-right: 30px">Card Number</th>
        		<th style="padding-right: 30px">Card Type</th>
        		<th style="padding-right: 30px">Balance</th>
        	</tr>`;
        
        data.forEach(c => {
            output += `
            	<tr>
            		<td>${c.cardNumber}</td>
            		<td>${c.cardType}</td>
            		<td>${c.balance}</td>
            	</tr>
            `
        });
        
        output += `</table>`;
        document.getElementById('custRespond').innerHTML = output;
        document.getElementById('message').innerHTML = " ";
    })
    return false;
}
// *******************************************************************************************//
// *******************************************************************************************//
// *******************************************************************************************//
function getPendingMoney() {
	fetch('http://localhost:2222/p1_reliable_bank/pendingmoney')
    .then((res) => res.json())
    .then((data) => {
        let output = '<h2>Pending Money List</h2>' + 
        `<table>
        	<tr>
        		<th style="padding-right: 30px">Sender</th>
        		<th style="padding-right: 30px">Amount</th>
        	</tr>`;
        
        data.forEach(t => {
            output += `
            	<tr>
            		<td>${t.sender}</td>
            		<td>${t.amount}</td>
            	</tr>
            `
        });
        
        output += `</table>`;
        output += `
	        <div>Enter the card number that you want to deposit to </div>
	        <input id="cardNumber" name="cardNumber" type="number" size="20">
	        
	        <div>Enter the transaction amount for approving/denying the transaction</div>
	        <input id="amount" name="amount" type="number">
	        
	        <br>
	        <button onclick="return doApprove();" class="btn btn-sm" type="submit">Approve</button>
	        <button onclick="return doDeny();" class="btn btn-sm" type="submit">Deny</button>
            `;
        
        document.getElementById('custRespond').innerHTML = output;
        document.getElementById('message').innerHTML = " ";
    })
    return false;
}

function doApprove() {
	if (document.getElementById('amount').value < 0) {
		let output = `<center><h5><span style='color: red;'>${document.getElementById('amount').value} is not a valid amount</span></h4></center>`;
		document.getElementById('message').innerHTML = output;
	}
	else {
		let data = {cardNumber: document.getElementById('cardNumber').value, 
				balance : document.getElementById('amount').value};
		fetch('http://localhost:2222/p1_reliable_bank/doapprove', {
	        method: 'POST',
	        redirect: 'follow',
	        headers: {
// 'Accept': 'application/json, text/plain, */*',
	            'Content-type': 'application/json'
	        },
	        body: JSON.stringify(data)
	    })
	    .then ((res) => {
	    	if(res.redirected) {
	    		window.location.href = res.url;
	    		alert('Invalid input. Please try again!');
	    	}
	    	return res.json();
	    })
	    .then((c) =>{
	    	let output = `<center><h5><span style='color: blue;'>The card ${c.cardNumber} now has a balance of \$${c.balance}</span></h4></center>`;
	    	document.getElementById('message').innerHTML = output;
	    })
	}
	
	return false;
}

function doDeny() {
	if (document.getElementById('amount').value < 0) {
		let output = `<center><h5><span style='color: red;'>${document.getElementById('amount').value} is not a valid amount</span></h4></center>`;
		document.getElementById('message').innerHTML = output;
	}
	else {
		let data = {cardNumber: document.getElementById('cardNumber').value, 
				balance : document.getElementById('amount').value};
		fetch('http://localhost:2222/p1_reliable_bank/dodeny', {
	        method: 'POST',
	        redirect: 'follow',
	        headers: {
// 'Accept': 'application/json, text/plain, */*',
	            'Content-type': 'application/json'
	        },
	        body: JSON.stringify(data)
	    })
	    .then ((res) => {
	    	if(res.redirected) {
	    		window.location.href = res.url;
	    		alert('Invalid input. Please try again!');
	    	}
	    	return res.json();
	    })
	    .then((c) => {
	    	let output = `<center><h5><span style='color: blue;'>The Pending Amount is Successfully Removed</span></h4></center>`;
	    	document.getElementById('message').innerHTML = output;
	    })
	}
	
	return false;
}
// *******************************************************************************************//
// *******************************************************************************************//
// *******************************************************************************************//
function applyNewCard() {
    let output = '<h2>Applying New Card</h2>' + 
    `
		<div>Enter your 9-digit customized card number </div>
        <input id="cardNumber" name="cardNumber" type="text" size="20">
        
        <div>Select card type </div>
        <select onchange="return select();" id="selection">
        	<option value="Credit Card">Credit Card</option>
        	<option value="Debit Card">Debit Card</option>
        </select>
        
        <div>Enter a valid starting balance </div>
        <input id="amount" name="amount" type="text">
        
        <br>
        <button onclick="return doApplyCard();" class="btn btn-sm" type="submit">Apply</button>
    `;

    document.getElementById('custRespond').innerHTML = output;
    document.getElementById('message').innerHTML = " ";
}

function doApplyCard() {
	if (document.getElementById('amount').value < 0) {
		let output = `<center><h5><span style='color: red;'>${document.getElementById('amount').value} is not a valid amount</span></h4></center>`;
		document.getElementById('message').innerHTML = output;
	}
	else {
		
		
		let data = {pd_cardNumber: document.getElementById('cardNumber').value,
				pd_cardType: document.getElementById('selection').value,
				pd_balance : document.getElementById('amount').value};
		fetch('http://localhost:2222/p1_reliable_bank/applycard', {
	        method: 'POST',
	        redirect: 'follow',
	        headers: {
// 'Accept': 'application/json, text/plain, */*',
	            'Content-type': 'application/json'
	        },
	        body: JSON.stringify(data)
	    })
	    .then ((res) => {
	    	if(res.redirected) {
	    		window.location.href = res.url;
	    		alert('Invalid input. Please try again!');
	    	}
	    	return res.json();
	    })
	    .then((c) => {
	    	let output = `<center><h5><span style='color: blue;'>Account Created. Please wait for approval!</span></h4></center>`;
	    	document.getElementById('message').innerHTML = output;
	    })
	}

	return false;
}
// *******************************************************************************************//
// *******************************************************************************************//
// *******************************************************************************************//
function deposit() {
	fetch('http://localhost:2222/p1_reliable_bank/mybank')
    .then((res) => res.json())
    .then((data) => {
        let output = '<h2>Deposit</h2>' + 
        `<table>
        	<tr>
        		<th style="padding-right: 30px">Card Number</th>
        		<th style="padding-right: 30px">Card Type</th>
        		<th style="padding-right: 30px">Balance</th>
        	</tr>`;
        
        data.forEach(c => {
            output += `
            	<tr>
            		<td>${c.cardNumber}</td>
            		<td>${c.cardType}</td>
            		<td>${c.balance}</td>
            	</tr>
            `
        });
        
        output += `</table>`;
        
        output += `
			<form action="deposit" method="POST">
				<div>Enter the card number that you want to deposit to </div>
		        <input id="cardNumber" name="cardNumber" type="number" size="20">
		        
		        <div>Enter a valid amount </div>
		        <input id="amount" name="amount" type="number">
		        
		        <br>
		        <button onclick="return doDeposit();" class="btn btn-sm" type="submit">Deposit</button>
		    </form> `;
        
        document.getElementById('custRespond').innerHTML = output;
        document.getElementById('message').innerHTML = " ";
    })
}

function doDeposit() {
	if (document.getElementById('amount').value < 0) {
		let output = `<center><h5><span style='color: red;'>${document.getElementById('amount').value} is not a valid amount</span></h4></center>`;
		document.getElementById('message').innerHTML = output;
	}
	else {
		let data = {cardNumber: document.getElementById('cardNumber').value, 
				balance : document.getElementById('amount').value};
		fetch('http://localhost:2222/p1_reliable_bank/deposit', {
	        method: 'POST',
	        redirect: 'follow',
	        headers: {
// 'Accept': 'application/json, text/plain, */*',
	            'Content-type': 'application/json'
	        },
	        body: JSON.stringify(data)
	    })
	    .then ((res) => {
	    	if(res.redirected) {
	    		window.location.href = res.url;
	    		alert('Invalid input. Please try again!');
	    	}
	    	return res.json();
	    })
	    .then((c) =>{
	    	let output = `<center><h5><span style='color: blue;'>The card ${c.cardNumber} now has a balance of \$${c.balance}</span></h4></center>`;
	    	document.getElementById('message').innerHTML = output;
	    })
	}

	return false;
}
// *******************************************************************************************//
// *******************************************************************************************//
// *******************************************************************************************//
function withdraw() {
	fetch('http://localhost:2222/p1_reliable_bank/mybank')
    .then((res) => res.json())
    .then((data) => {
        let output = '<h2>Withdraw</h2>' + 
        `<table>
        	<tr>
        		<th style="padding-right: 30px">Card Number</th>
        		<th style="padding-right: 30px">Card Type</th>
        		<th style="padding-right: 30px">Balance</th>
        	</tr>`;
        
        data.forEach(c => {
            output += `
            	<tr>
            		<td>${c.cardNumber}</td>
            		<td>${c.cardType}</td>
            		<td>${c.balance}</td>
            	</tr>
            `
        });
        
        output += `</table>`;
        
        output += `
			<form action="withdraw" method="POST">
				<div>Enter the card number that you want to withdraw from </div>
		        <input id="cardNumber" name="cardNumber" type="number" size="20">
		        
		        <div>Enter a valid amount </div>
		        <input id="amount" name="amount" type="number">
		        
		        <br>
		        <button onclick="return doWithdraw();" class="btn btn-sm" type="submit">Withdraw</button>
		    </form> `;
        
        document.getElementById('custRespond').innerHTML = output;
        document.getElementById('message').innerHTML = " ";
    })
}

function doWithdraw() {
	if (document.getElementById('amount').value < 0) {
		let output = `<center><h5><span style='color: red;'>${document.getElementById('amount').value} is not a valid amount</span></h4></center>`;
		document.getElementById('message').innerHTML = output;
	}
	else {
		let data = {cardNumber: document.getElementById('cardNumber').value, 
				balance : document.getElementById('amount').value};
		fetch('http://localhost:2222/p1_reliable_bank/withdraw', {
	        method: 'POST',
	        redirect: 'follow',
	        headers: {
// 'Accept': 'application/json, text/plain, */*',
	            'Content-type': 'application/json'
	        },
	        body: JSON.stringify(data)
	    })
	    .then ((res) => {
	    	if(res.redirected) {
	    		window.location.href = res.url;
	    		alert('Invalid input. Please try again!');
	    	}
	    	return res.json();
	    })
	    .then((c) =>{
	    	let output = `<center><h5><span style='color: blue;'>The card ${c.cardNumber} now has a balance of \$${c.balance}</span></h4></center>`;
	    	document.getElementById('message').innerHTML = output;
	    })
	}
	
	return false;
}
// *******************************************************************************************//
// *******************************************************************************************//
// *******************************************************************************************//
function transfer() {
	fetch('http://localhost:2222/p1_reliable_bank/mybank')
    .then((res) => res.json())
    .then((data) => {
        let output = '<h2>Transfer</h2>' + 
        `<table>
        	<tr>
        		<th style="padding-right: 30px">Card Number</th>
        		<th style="padding-right: 30px">Card Type</th>
        		<th style="padding-right: 30px">Balance</th>
        	</tr>`;
        
        data.forEach(c => {
            output += `
            	<tr>
            		<td>${c.cardNumber}</td>
            		<td>${c.cardType}</td>
            		<td>${c.balance}</td>
            	</tr>
            `
        });
        
        output += `</table>`;
        
        output += `
			<form action="transfer" method="POST">
				<div>Enter the card number that you want to withdraw from </div>
		        <input id="cardNumber1" name="cardNumber1" type="number" size="20">
		        
		        <div>Enter the card number that you want to deposit to </div>
		        <input id="cardNumber2" name="cardNumber2" type="number" size="20">
		        
		        <div>Enter a valid amount </div>
		        <input id="amount" name="amount" type="number">
		        
		        <br>
		        <button onclick="return doTransfer();" class="btn btn-sm" type="submit">Transfer</button>
		    </form> `;
        
        document.getElementById('custRespond').innerHTML = output;
        document.getElementById('message').innerHTML = " ";
    })
}

function doTransfer() {
	if (document.getElementById('amount').value < 0) {
		let output = `<center><h5><span style='color: red;'>${document.getElementById('amount').value} is not a valid amount</span></h4></center>`;
		document.getElementById('message').innerHTML = output;
	}
	else {
		let data = {sender: document.getElementById('cardNumber1').value, 
				amount : document.getElementById('amount').value,
				receiver : document.getElementById('cardNumber2').value};
		fetch('http://localhost:2222/p1_reliable_bank/transfer', {
	        method: 'POST',
	        redirect: 'follow',
	        headers: {
// 'Accept': 'application/json, text/plain, */*',
	            'Content-type': 'application/json'
	        },
	        body: JSON.stringify(data)
	    })
	    .then ((res) => {
	    	if(res.redirected) {
	    		window.location.href = res.url;
	    		alert('Invalid input. Please try again!');
	    	}
	    	return res.json();
	    })
	    .then((c) =>{
	    	let output = `<center><h5><span style='color: blue;'>Transferred Successfully</span></h4></center>`;
	    	document.getElementById('message').innerHTML = output;
	    })
	}
	
	return false;
}
