function userValidation(form)
{
    // event.preventDefault(); // this will prevent the submit event.
    // if (document.userValidation.delete) {
    //     document.userValidation.submit();
    //     return true;
    // }
    if (!form.name.value.match(/^[A-Z]{1}[a-z]{0,} [A-Z]{1}[a-z]{0,}$/)) {
        alert("Name contains two words beginning with uppercase letters");
        document.userValidation.name.focus();
        return false;
    }
    if (!form.email.value.match(/^[a-z]{1,}[a-z0-9.]{0,}[a-z0-9]{1,}@[a-z]{1,}[a-z0-9]{0,}\.[a-z]{1,}$/)) {
        alert("E-mail is a sequence of letters, digits and points separated by @");
        document.userValidation.email.focus();
        return false;
    }
    if (!form.login.value.match(/^[a-zA-Z0-9_]{1,}$/)) {
        alert("Login is a sequence of letters, digits and underscore symbols");
        document.userValidation.login.focus();
        return false;
    }
    if (!form.password.value.match(/^[a-zA-Z0-9!@#\$%\^\&*\)\(+=._-]$/)) {
        alert("Password is a sequence of letters, digits and special symbols");
        document.userValidation.login.focus();
        return false;
    }
    if (!parseInt(form.role.value) > 0) {
        alert("Role is a positive number...");
        document.userValidation.role.focus();
        return false;
    }

    return true;

    // if (!document.userValidation.name.value.match(/^[A-Z]{1}[a-z]{0,} [A-Z]{1}[a-z]{0,}$/)) {
    //     alert("Name contains two words beginning with uppercase letters");
    //     document.userValidation.name.focus();
    //     return false;
    // }
    // if (!document.userValidation.email.value.match(/^[a-z]{1,}[a-z0-9.]{0,}[a-z0-9]{1,}@[a-z]{1,}[a-z0-9]{0,}\.[a-z]{1,}$/)) {
    //     alert("E-mail is a sequence of letters, digits and points separated by @");
    //     document.userValidation.email.focus();
    //     return false;
    // }
    // if (!document.userValidation.login.value.match(/^[a-zA-Z0-9_]{1,}$/)) {
    //     alert("Login is a sequence of letters, digits and underscore symbols");
    //     document.userValidation.login.focus();
    //     return false;
    // }
    // if (!document.userValidation.password.value.match(/^[a-zA-Z0-9!@#\$%\^\&*\)\(+=._-]+$/)) {
    //     alert("Password is a sequence of letters, digits and special symbols");
    //     document.userValidation.login.focus();
    //     return false;
    // }
    // if (!parseInt(document.userValidation.role.value) > 0) {
    //     alert("Role is a positive number...");
    //     document.userValidation.role.focus();
    //     return false;
    // }
    //
    // document.userValidation.edit();// fire submit event
    // return true;
}