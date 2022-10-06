const out = (any) => console.log(any)
out("test")

const url = "http://localhost:8080/api"

async function fetchData(endpoint, method, body) {
    const response = await fetch(endpoint, {
        method: method,
        body: body,
        headers: {
            "Content-type": "application/json"
        }
    })
    //out(data)
    return response.json()
}

// fetchData(url, "GET", null)
//fetchData(url+"/find?id=1", "GET", null)
//
// const userBody = JSON.stringify({
//         id: 1,
//         username: "updatedUsername",
//         email: "updatedPassword",
//         password: "updatedPassword"
//     })
// fetchData(url, "POST", userBody)

// const newUserBody = JSON.stringify({
//     username: "newUser",
//     email: "newEmail",
//     password: "newPassword"
// })
//fetchData(url+"/save", "POST", newUserBody)

//fetchData(url+"/delete?id=1", "GET", null)

let inputIdToFind = document.getElementById("inputIdToFind")
const buttonFind = document.getElementById("buttonFind")
let textFieldId = document.getElementById("textFieldId")
let textFieldUsername = document.getElementById("textFieldUsername")
let textFieldPassword = document.getElementById("textFieldPassword")
let textFieldEmail = document.getElementById("textFieldEmail")

buttonFind.addEventListener('click', clickF)

function clickF() {
    getAndFillOut(inputIdToFind.value)
}

function getAndFillOut(id) {
    fetchData(url + "/find?id=" + id, "GET", null).then(result => {
        out(result)
        textFieldId.value = result.id
        textFieldUsername.value = result.username
        textFieldPassword.value = result.password
        textFieldEmail.value = result.email
    })
}

const userBody = (username, password, email) => {
    return JSON.stringify({
        username: username,
        password: password,
        email: email
    })
}

function create() {
    fetchData(url + "/save", "POST", userBody(textFieldUsername.value, textFieldPassword.value, textFieldEmail.value))
        .then(result => out(result))
}

const buttonToCreate = document.getElementById("buttonToCreate")
buttonToCreate.addEventListener('click', create)




