// for selecting different controls
const petForm = document.getElementById("registerForm");
const petName = document.querySelector(".petName");
const species = document.querySelector(".species");
const breed = document.querySelector(".breed");
const gender = document.querySelector(".gender");
const size = document.querySelector(".size");
const fur = document.querySelector(".fur");
const tutorName = document.querySelector(".tutorName");
const tutorPhone = document.querySelector(".tutorPhone");
const petService = document.querySelector(".petService");
const petsTable = document.getElementById("petsTable");
const editForm = document.getElementById("editBtn");
const searchButton = document.querySelector('#search-button');
const searchInput = document.querySelector('#search-input');

// function for registering pet
function registerPet() {
  fetch("http://localhost:8080/pet-shop/add", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      petName: petName.value,
      species: species.value,
      breed: breed.value,
      gender: gender.value,
      size: size.value,
      fur: fur.value,
      tutorName: tutorName.value,
      tutorPhone: tutorPhone.value,
      petService: petService.value,
    }),
  })
    .then(function (res) {
      window.location.href = window.location.href;
    })
    .catch(function (res) {
      console.log("Ops, something went wrong. Try it again!");
    });
}

// function for showing data
fetch("http://localhost:8080/pet-shop/all")
  .then((response) => response.json())
  .then((data) => {
    const tableBody = document.querySelector("#petsTable tbody");

    data.forEach((result) => {
      const row = document.createElement("tr");
      row.innerHTML = `
            <td>${result.id}</td>
            <td>${result.petName}</td>
            <td>${result.species}</td>
            <td>${result.breed}</td>
            <td>${result.gender}</td>
            <td>${result.size}</td>
            <td>${result.fur}</td>
            <td>${result.tutorName}</td>
            <td>${result.tutorPhone}</td>
            <td>${result.petService}</td>
            <td><button type="button" class="btn btn-success btn-sm edBtn" data-bs-toggle="modal" data-bs-target="#modalWindow" data-pet-edit="${result.id}">
            <span class="bi-pencil-square" style="color: #fff" aria-hidden="true"></span></button>
            <button type="button" class="btn btn-danger btn-sm deleteBtn" data-pet="${result.id}">
            <span class="bi-trash" style="color: #fff" aria-hidden="true"></span></button>
            </td>
            `;
      tableBody.appendChild(row);
    });
  })
  .then(() => {
// event for deleting pet 
const delBtn = document.querySelectorAll(".deleteBtn");
delBtn.forEach((
  deleteButton => {
    deleteButton.addEventListener("click", function (event) {
      const petId = event.currentTarget.dataset.pet
      deletePet(petId);
      console.log(event)
    });
  }
  ));
  })
  .then(()=>{
// event for editing pet
const editBtn = document.querySelectorAll(".edBtn");
editBtn.forEach((
  editButton => {
    editButton.addEventListener("click", function (event){
      const petId = event.currentTarget.dataset.pet
      editPet(id);
      console.log(event)
    })
  }
))
  })
  .catch(error => console.error(error));

 
// function for editing pet
function editPet(id){
  fetch(`http://localhost:8080/pet-shop/update/${id}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      petName: petName.value,
      species: species.value,
      breed: breed.value,
      gender: gender.value,
      size: size.value,
      fur: fur.value,
      tutorName: tutorName.value,
      tutorPhone: tutorPhone.value,
      petService: petService.value,
    })
  })
  .then(function (res) {
    window.location.href = window.location.href;
  })
  .catch(function (res) {
    console.log("Ops, something went wrong. Try it again!")
  })
}
  
// function for deleting pet
  function deletePet(id){
    fetch(`http://localhost:8080/pet-shop/delete/${id}`,{
        method: "DELETE",
    })
    .then(function (res) {
      window.location.href = window.location.href;
    })
    .catch(function (res) {
      console.log("Ops, something went wrong. Try it again!")
    })
    }

// function for searching pet by name
function searchPet(name) {
  const tbody = document.querySelector('#petsTable tbody');

  for (const row of tbody.rows) {
    const nameCell = row.cells[1];
  
    if (nameCell.textContent.toLowerCase().includes(name.toLowerCase())) {
      row.style.display = '';
    } else {
      row.style.display = 'none';
    }
  }
}

// event for registering pet
petForm.addEventListener("submit", function (event) {
    event.preventDefault();
    registerPet();
  });


// event for searching pet
searchButton.addEventListener('click', (event) => {
  event.preventDefault();
  searchPet(searchInput.value);
});
