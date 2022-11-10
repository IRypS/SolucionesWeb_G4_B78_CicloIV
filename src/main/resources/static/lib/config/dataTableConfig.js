$(document).ready(function () {
    $('#dataTable').DataTable({
        "language": {
            "url": "/lib/DataTables/DataTables-1.12.1/plugins/spanish_es-ES.json"
        },
        paging: true,
        ordering: true,
        info: true,
        responsive: true
    });
});