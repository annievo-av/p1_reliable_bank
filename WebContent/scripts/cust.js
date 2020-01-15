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
    })
}

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
        document.getElementById('custRespond').innerHTML = output;
    })
}

function applyNewCard() {
    let output = '<h2>Applying New Card</h2>' + 
    `
		<form action="applycard" method="POST">
			<div>Enter your 9-digit customized card number </div>
	        <input name="cardNumber" type="text" size="20">
	        
	        <div>Select card type </div>
	        <select>
	        	<option name="cc" value="Credit Card">Credit Card</option>
	        	<option name="db" value="Debit Card">Debit Card</option>
	        </select>
	        
	        <div>Enter a valid starting balance </div>
	        <input name="amount" type="text">
	        
	        <br>
	        <button class="btn btn-sm" type="submit">Apply</button>
	    </form>
    `;

    document.getElementById('custRespond').innerHTML = output;
}

