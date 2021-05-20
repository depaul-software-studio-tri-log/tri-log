const wireChangePasswordButton = () => {
    $('#changePasswordBTN').off();
    $('#changePasswordBTN').on('click', () => {

        const url = '/api/change-password';
            const options = {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    oldPassword: $("#oldPasswordTB").val().trim(),
                    newPassword: $("#newPasswordTB").val().trim()
                })
            };

            fetch(url, options)
              .then(response => {
                return response.json();
              })
              .then(response => {
                alert(response);
              });

    });
}

$("#oldPasswordTB").keyup(() => {
    setChangePasswordEnabled();
});

$("#newPasswordTB").keyup(() => {
    setChangePasswordEnabled();
});

$("#verifyPasswordTB").keyup(() => {
    setChangePasswordEnabled();
});


window.showChangePasswordModal = () => {
    wireChangePasswordButton();
    $("#oldPasswordTB").val("");
    $("#newPasswordTB").val("");
    $("#verifyPasswordTB").val("");
    setChangePasswordEnabled();
    $('#changePasswordModal').modal();
}

const setChangePasswordEnabled = () => {
    if ($("#oldPasswordTB").val().length > 0 &&
            $("#newPasswordTB").val().length > 0 &&
            $("#verifyPasswordTB").val().length > 0 &&
            $("#newPasswordTB").val() === $("#verifyPasswordTB").val()) {
        $('#changePasswordBTN').removeAttr("disabled");
    }
    else {
        $('#changePasswordBTN').attr("disabled", "disabled");
    }
}



