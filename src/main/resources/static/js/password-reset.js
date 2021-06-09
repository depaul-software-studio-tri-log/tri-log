$("#newPasswordTB").keyup(() => {
    setChangePasswordEnabled();
});

$("#verifyPasswordTB").keyup(() => {
    setChangePasswordEnabled();
});

const setChangePasswordEnabled = () => {
    if ($("#newPasswordTB").val().length > 0 &&
            $("#verifyPasswordTB").val().length > 0 &&
            $("#newPasswordTB").val() === $("#verifyPasswordTB").val()) {
        $('#submitBTN').removeAttr("disabled");
    }
    else {
        $('#submitBTN').attr("disabled", "disabled");
    }
};

setChangePasswordEnabled();