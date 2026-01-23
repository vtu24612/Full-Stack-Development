// Sample data to show in tables (Mock Database)
const vehicles = [
    { vin: 'VIN1001', make: 'Toyota', model: 'Camry', year: 2024, price: 30000, status: 'Sold' },
    { vin: 'VIN1002', make: 'Honda', model: 'Civic', year: 2023, price: 25000, status: 'Available' },
    { vin: 'VIN1003', make: 'Ford', model: 'Mustang', year: 2024, price: 55000, status: 'Available' }
];

const customers = [
    { id: 1, first: 'Alice', last: 'Smith', email: 'alice@example.com', phone: '9876543210' },
    { id: 2, first: 'Bob', last: 'Johnson', email: 'bob@example.com', phone: '8765432109' }
];

// When the page loads, show the data in tables
document.addEventListener('DOMContentLoaded', () => {
    renderTable('vehicle-table-body', vehicles, ['vin', 'make', 'model', 'year', 'price', 'status']);
    renderTable('customer-table-body', customers, ['id', 'first', 'email', 'phone']);
    updateDashboardStats();
});

// Function to switch between different menu items
function showSection(sectionId) {
    // Hide all sections first
    document.querySelectorAll('.section').forEach(sec => sec.classList.remove('active-section'));
    
    // Show the one we clicked
    document.getElementById(sectionId).classList.add('active-section');
    
    // Update the sidebar colors
    document.querySelectorAll('.sidebar li').forEach(li => li.classList.remove('active'));
    event.currentTarget.classList.add('active');
}

// Function to put data rows into HTML tables
function renderTable(elementId, dataArray, columns) {
    const tbody = document.getElementById(elementId);
    tbody.innerHTML = ''; 

    dataArray.forEach(item => {
        let rowHtml = '<tr>';
        columns.forEach(col => {
            if (col === 'first') {
                rowHtml += `<td>${item.first} ${item.last}</td>`;
            } else if(col === 'price') {
                rowHtml += `<td>$${item.price.toLocaleString()}</td>`;
            } else {
                rowHtml += `<td>${item[col]}</td>`;
            }
        });
        rowHtml += '</tr>';
        tbody.innerHTML += rowHtml;
    });
}

// Calculate the numbers for the dashboard cards
function updateDashboardStats() {
    document.getElementById('total-cars').innerText = vehicles.length;
    
    const soldCount = vehicles.filter(v => v.status === 'Sold').length;
    document.getElementById('total-sold').innerText = soldCount;

    const revenue = vehicles
        .filter(v => v.status === 'Sold')
        .reduce((sum, car) => sum + car.price, 0);
    document.getElementById('total-revenue').innerText = "$" + revenue.toLocaleString();
}

// Modal open/close logic
function openModal(id) { document.getElementById(id).style.display = 'flex'; }
function closeModal(id) { document.getElementById(id).style.display = 'none'; }

// Logic for the 'Add Vehicle' form
document.getElementById('addVehicleForm').addEventListener('submit', function(e) {
    e.preventDefault();
    
    // Get values from inputs
    const newCar = {
        vin: document.getElementById('vin').value,
        make: document.getElementById('make').value,
        model: document.getElementById('model').value,
        year: document.getElementById('year').value,
        price: Number(document.getElementById('price').value),
        status: 'Available'
    };

    // Push into our array and refresh table
    vehicles.push(newCar);
    renderTable('vehicle-table-body', vehicles, ['vin', 'make', 'model', 'year', 'price', 'status']);
    updateDashboardStats();
    closeModal('vehicleModal');
    this.reset(); // Clear the form
});