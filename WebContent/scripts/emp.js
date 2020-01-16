function getAccounts() {
	fetch('http://localhost:2222/p1_reliable_bank/accounts')
    .then((res) => res.json())
    .then((data) => {
        let output = '<h2>Accounts</h2>' + 
        `<table>
        	<tr>
        		<th style="padding-right: 30px">Username</th>
        		<th style="padding-right: 30px">User Type</th>
        		<th style="padding-right: 30px">Full Name</th>
        		<th style="padding-right: 30px">Email</th>
        		<th style="padding-right: 30px">Card Number</th>
        		<th style="padding-right: 30px">Card Type</th>
        		<th style="padding-right: 30px">Balance</th>
        	</tr>`;
        
        data.forEach(a => {
            output += `
            	<tr>
            		<td>${a.username}</td>
            		<td>${a.usertype}</td>
            		<td>${a.fullname}</td>
            		<td>${a.email}</td>
            		<td>${a.card.cardNumber}</td>
            		<td>${a.card.cardType}</td>
            		<td>${a.card.balance}</td>
            	</tr>
            `
        });
        
        output += `</table>`;
        document.getElementById('empRespond').innerHTML = output;
    })
}

function getPendingAccounts() {
	fetch('http://localhost:2222/p1_reliable_bank/pendingaccounts')
	.then((res) => res.json())
    .then((data) => {
        let output = '<h2>Pending Accounts</h2>' + 
        `
        <span style="color: blue">Enter the username for approving/denying the pending user account</span>
        <form action="accountapproval" method="GET">
	        <input name="pendingUsername" type="text" placeholder="Pending Username" size="20">
	        <button class="btn btn-sm" type="submit">Approve</button>
        </form>
        <form action="accountdeny" method="GET">
	        <input name="pendingUsername" type="text" placeholder="Pending Username" size="20">
	        <button class="btn btn-sm" type="submit">Deny</button>
        </form>
	    `
        +
        `<table>
        	<tr>
        		<th style="padding-right: 30px">Username</th>
        		<th style="padding-right: 30px">Password</th>
        		<th style="padding-right: 30px">User Type</th>
        		<th style="padding-right: 30px">Full Name</th>
        		<th style="padding-right: 30px">Email</th>
        	</tr>`;
        
        data.forEach(a => {
            output += `
            	<tr>
            		<td>${a.pd_username}</td>
            		<td>${a.pd_password}</td>
            		<td>${a.pd_usertype}</td>
            		<td>${a.pd_fullname}</td>
            		<td>${a.pd_email}</td>
            	</tr>
            `
        });
        
        output += `</table>`;
        document.getElementById('empRespond').innerHTML = output;
    })
}

function getPendingCards(){
	fetch('http://localhost:2222/p1_reliable_bank/pendingcards')
	.then((res) => res.json())
	.then((data) => {
		let output = '<h2>Pending Cards</h2>' + 
        `
        <span style="color: blue">Enter the card number for approving/denying the pending account</span>
        <form action="cardapproval" method="GET">
	        <input name="cardOption" type="text" placeholder="Card Number" size="20">
	        <button class="btn btn-sm" type="submit">Approve</button>
        </form>
        <form action="carddeny" method="GET">
	        <input name="cardOption" type="text" placeholder="Card Number" size="20">
	        <button class="btn btn-sm" type="submit">Deny</button>
        </form>
	    `
        +
        `<table>
        	<tr>
        		<th style="padding-right: 30px">Applicator</th>
        		<th style="padding-right: 30px">Card Number</th>
        		<th style="padding-right: 30px">Card Type</th>
        		<th style="padding-right: 30px">Balance</th>
        	</tr>`;
        
        data.forEach(c => {
            output += `
            	<tr>
            		<td>${c.applicator}</td>
            		<td>${c.pd_cardNumber}</td>
            		<td>${c.pd_cardType}</td>
            		<td>${c.pd_balance}</td>
            	</tr>
            `
        });
        
        output += `</table>`;
        document.getElementById('empRespond').innerHTML = output;
	})
}

function getlog() {
	fetch('http://localhost:2222/p1_reliable_bank/log')
    .then((res) => res.json())
    .then((data) => {
        let output = '<h2>Transactions Log</h2>' + 
        `<table>
        	<tr>
        		<th style="padding-right: 30px">Person 1</th>
        		<th style="padding-right: 30px">Action</th>
        		<th style="padding-right: 30px">Person 2</th>
        		<th style="padding-right: 30px">Time</th>
        	</tr>`;
        
        data.forEach(t => {
            output += `
            	<tr>
            		<td>${t.person_1}</td>
            		<td>${t.action}</td>
            		<td>${t.person_2}</td>
            		<td>${t.time}</td>
            	</tr>
            `
        });
        
        output += `</table>`;
        document.getElementById('empRespond').innerHTML = output;
    })
}
