function deleteTask(projectId) {
  const url = '/TacheServlet?id=' + projectId; // Append the project ID to the URL
  fetch(url, {
      method: 'DELETE'
  })
      .then(data => {
          var toastEl = document.getElementById('myToast');
          var toastBody = toastEl.querySelector('.toast-body');
          toastBody.textContent = 'Success to delete the task';
          var toast = new bootstrap.Toast(toastEl);
          toast.show();
          setTimeout(() => {
              location.reload();
          }, 3000);
      })

}
