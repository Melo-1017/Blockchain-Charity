var Web3 = require('web3');
var fs = require("fs");
web3 = new Web3(new Web3.providers.HttpProvider("http://localhost:12345"));
var data = fs.readFileSync("./storage.json", "utf-8");

const account1 = '0xaaB9CE2544758C4f1336bC888E98B16ee2d05E0C'

var contract = new web3.eth.Contract(JSON.parse(data),'0xb3D513410A136f0F5442006094E53c681Fd4F1AD');

let txo = {
    from: account1,
    to: "0xb3D513410A136f0F5442006094E53c681Fd4F1AD",
    value:'0x00',
    data: contract.methods.declaring(2,"2022/10/23","qwerty").encodeABI()
}

web3.eth.sendTransaction(txo, (err, txHash) => {
    console.log('txHash:', txHash)
}).then(txhash=>{
    contract.methods.getinfo(2).call((err,log)=>{
        console.log(log)
    });
})

