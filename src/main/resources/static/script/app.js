let deleteLink = document.getElementsByClassName("delete");
let confirm_delete = document.getElementById("confirm-delete");
let confirm = document.getElementById("confirm");


for(let i = 0; i < deleteLink.length; i ++){
    delLink = deleteLink[i];
     // console.log(`Node value ${delLink.parentNode.children[0].innerHTML}`)
    delLink.addEventListener('click', function (){

        confirm_delete.style.display="flex";

        confirm.setAttribute("href", `/employee/delete/25`);
        // // confirm.setAttribute("id", "delete")// id="delete"></a>';
        // console.log(confirm.innerHTML);
    });
}