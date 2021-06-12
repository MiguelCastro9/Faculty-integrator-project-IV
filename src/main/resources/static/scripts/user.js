$(document).ready(function () {
    clearClickEditWithNewUser();
    setClickWithEdit();
    disableEmailInput();
});

const clearClickEditWithNewUser = () => {
    $(".newUser").click(function () {
        sessionStorage.removeItem("editUser");
    });
};

const setClickWithEdit = () => {
    $(".bi-pencil").click(function () {
        sessionStorage.setItem('editUser', 'true');
    });
};

const disableEmailInput = () => {
    if (sessionStorage.getItem("editUser") === "true") {
        $('#email').attr('readonly', 'true');
    }
};