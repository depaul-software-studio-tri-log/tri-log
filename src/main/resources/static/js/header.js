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

window.showChangePasswordModal = () => {
    wireChangePasswordButton();
    $("#oldPasswordTB").val("");
    $("#newPasswordTB").val("");
    $("#verifyPasswordTB").val("");
    $('#changePasswordModal').modal();
}

