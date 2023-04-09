var Web3 = require('web3');
var Personal = require('web3-eth-personal');
web3 = new Web3(new Web3.providers.HttpProvider("http://localhost:12345"));
var str="日本东京 上海海洋大学";
let data = web3.utils.toHex(str);
console.log(data);
console.log(web3.utils.hexToString(data));
var address;
let txo = {
    from: web3.eth.accounts[0],
    to: web3.eth.accounts[0],
    value:'0x00',
    data: data
}


const myFunc = async () => {
    try {
        return await web3.eth.getAccounts();
    } catch (err) {
        console.log(err);
    }
}

myFunc().then(address=>{
    txo.from=address[0];
    txo.to=address[0];
    web3.eth.getTransaction('0x38458bb724755724293f7237ed6fbb9f0312e629d5b2e02cab29472c7b807329',
        ((error, transaction) => {
            console.log(transaction);
        }))
    /*web3.eth.sendTransaction(txo,  (error, hash) =>  {
        console.log(hash);
        web3.eth.getTransaction(hash)
            .then(console.log);
    });*/
});







