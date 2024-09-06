function insertData(id) {
    fetch('/detail', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ dataId: id }),
    })
        .then(response => response.json())
        .catch(error => {
            console.error('Error:', error);
        });
}