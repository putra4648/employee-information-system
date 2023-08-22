let EmployeeTable = new DataTable("#employeeTable", {
  responsive: true,
  ajax: {
    url: '/api/v1/employees',
    method: "POST",
    data: function(params) {
      const filterEmployeeParams = {
        employee_id: $("input[name='employee_id']").val(),
        employee_fullname: $("input[name='employee_fullname']").val(),
        employee_birthdate: $("input[name='employee_birthdate']").val(),
        employee_hiredate: $("input[name='employee_hiredate']").val()
      }

      const sortby = params.columns[params.order[0].column]
      const dir = params.order[0].dir
      const filterParams = {
        page: params.start,
        size: params.length,
        sortBy: sortby.data,
        dir: dir,
        employee_filter: JSON.stringify(filterEmployeeParams),
      }
      return filterParams;
    },
    dataSrc: function(json) {
      return json.data;
    }
  },
  processing: true,
  serverSide: true,
  paging: true,
  searching: false,
  columns: [
    {
      data: "id",
    },
    {
      data: 'fullname'
    },
    {
      data: "birthdate",
      render: function(data) {
        return formatTimestampToDate(data)
      }
    },
    {
      data: 'hiredate',
      render: function(data) {
        return formatTimestampToDate(data)
      }
    },
    {
      data: null,
    },
  ],
  columnDefs: [
    {
      targets: 4,
      sortable: false,
      render: function(data, type, row, meta) {
        return "<a href='/detail' class='btn'>Detail</a>";
      }
    }
  ],
  dom: "<'float-end'l>" + 'rtip'
})
