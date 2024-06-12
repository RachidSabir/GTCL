function deleteUser(userId) {
    const url = '/UtilisateurServlet?id=' + userId; // Append the project ID to the URL
    fetch(url, {
        method: 'DELETE'
    })
        .then(data => {
            var toastEl = document.getElementById('myToast');
            var toastBody = toastEl.querySelector('.toast-body');
            toastBody.textContent = 'la suppression de l\'utilisateur a passé avec succés';
            var toast = new bootstrap.Toast(toastEl);
            toast.show();
            setTimeout(() => {
                location.reload();
            }, 3000);
        })
}
