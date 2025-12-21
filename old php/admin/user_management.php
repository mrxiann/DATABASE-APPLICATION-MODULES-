<?php
// admin_user_management.php
require_once('admin_sidebar.php');

// PHP Data Stubs (Replace with actual database user list)
// 'barangay' is now 'purok_address' for consistency
$users = [
    ['name' => 'Juan D. Dela Cruz', 'role' => 'Youth', 'status' => 'Verified', 'purok_address' => 'Purok 5', 'age' => 22],
    ['name' => 'Maria S. Santos', 'role' => 'Youth', 'status' => 'Pending', 'purok_address' => 'Purok 2', 'age' => 17],
    ['name' => 'Benito M. Reyes', 'role' => 'Admin', 'status' => 'Active', 'purok_address' => 'SK Council', 'age' => 25],
    ['name' => 'Crispin G. Diaz', 'role' => 'Youth', 'status' => 'Verified', 'purok_address' => 'Purok 1', 'age' => 20],
    ['name' => 'Sara M. Luna', 'role' => 'Youth', 'status' => 'Pending', 'purok_address' => 'Purok 5', 'age' => 19],
    ['name' => 'Ricardo Z. Ramos', 'role' => 'Youth', 'status' => 'Verified', 'purok_address' => 'Purok 3', 'age' => 23],
];

// Dynamically collect unique Purok/Address values for the filter
$purok_addresses = array_unique(array_column($users, 'purok_address'));
sort($purok_addresses); 

// PHP Logic for Status Display
function get_status_badge($status) {
    switch ($status) {
        case 'Verified':
            return '<span class="px-2 inline-flex text-xs leading-5 font-bold rounded-full bg-green-100 text-green-700">Verified</span>';
        case 'Pending':
            // Changed to Yellow for better visual distinction from Admin status
            return '<span class="px-2 inline-flex text-xs leading-5 font-bold rounded-full bg-yellow-100 text-yellow-700">Pending</span>'; 
        case 'Active':
            return '<span class="px-2 inline-flex text-xs leading-5 font-bold rounded-full bg-blue-100 text-blue-700">Active (Admin)</span>';
        default:
            return '<span class="px-2 inline-flex text-xs leading-5 font-bold rounded-full bg-gray-100 text-gray-700">' . htmlspecialchars($status) . '</span>';
    }
}
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Management | SK Admin Portal</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        @media (min-width: 1024px) {
            .lg-main-content {
                margin-left: 16rem; /* ml-64 */
            }
        }
    </style>
</head>
<body class="bg-gray-100 font-sans">
    <div class="flex min-h-screen">
        
        <?php render_admin_sidebar('users'); ?>

        <main class="flex-1 p-4 md:p-8 lg:p-10 overflow-y-auto lg-main-content">
            
            <div class="mb-8">
                <h1 class="text-4xl font-extrabold text-gray-900 mb-2 flex items-center">
                    <i class="fas fa-users-cog mr-3 text-blue-600"></i> User Management
                </h1>
                <p class="text-gray-500">Manage all registered SK Youth and Admin accounts, including verification and detail updates.</p>
            </div>

            <div class="bg-white p-6 md:p-8 rounded-xl shadow-2xl border border-gray-200">
                
                <div class="flex flex-col md:flex-row justify-start items-start md:items-center mb-6 space-y-4 md:space-y-0 md:space-x-4">
                    
                    <input type="text" id="search-input" placeholder="Search by name, purok, or ID..." 
                           class="w-full md:max-w-xs py-2 px-4 border border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500 transition duration-150 ease-in-out shadow-sm">
                    
                    <div class="flex flex-wrap gap-3">
                        
                        <select id="role-filter" class="filter-select py-2 px-4 border border-gray-300 rounded-lg text-sm shadow-sm hover:border-blue-400 cursor-pointer transition">
                            <option value="">All Roles</option>
                            <option value="Youth">Youth</option>
                            <option value="Admin">Admin</option>
                        </select>
                        
                        <select id="status-filter" class="filter-select py-2 px-4 border border-gray-300 rounded-lg text-sm shadow-sm hover:border-blue-400 cursor-pointer transition">
                            <option value="">All Statuses</option>
                            <option value="Verified">Verified</option>
                            <option value="Pending">Pending</option>
                            <option value="Active">Active (Admin)</option>
                        </select>

                        <select id="purok-filter" class="filter-select py-2 px-4 border border-gray-300 rounded-lg text-sm shadow-sm hover:border-blue-400 cursor-pointer transition">
                            <option value="">All Purok/Address</option>
                            <?php foreach ($purok_addresses as $address): ?>
                                <option value="<?php echo htmlspecialchars($address); ?>"><?php echo htmlspecialchars($address); ?></option>
                            <?php endforeach; ?>
                        </select>
                    </div>
                </div>
                
                <div class="overflow-x-auto">
                    <table class="min-w-full divide-y divide-gray-200">
                        <thead class="bg-blue-50">
                            <tr>
                                <th scope="col" class="px-6 py-3 text-left text-xs font-semibold text-blue-700 uppercase tracking-wider">Name</th>
                                <th scope="col" class="px-6 py-3 text-left text-xs font-semibold text-blue-700 uppercase tracking-wider">Role</th>
                                <th scope="col" class="px-6 py-3 text-left text-xs font-semibold text-blue-700 uppercase tracking-wider">Status</th>
                                <th scope="col" class="px-6 py-3 text-left text-xs font-semibold text-blue-700 uppercase tracking-wider">Purok/Address</th>
                                <th scope="col" class="px-6 py-3 text-left text-xs font-semibold text-blue-700 uppercase tracking-wider">Age</th>
                                <th scope="col" class="px-6 py-3 text-right text-xs font-semibold text-blue-700 uppercase tracking-wider">Actions</th>
                            </tr>
                        </thead>
                        <tbody id="user-table-body" class="bg-white divide-y divide-gray-100">
                            
                            <?php foreach ($users as $user): ?>
                            <tr class="user-row hover:bg-gray-50 transition duration-150" 
                                data-name="<?php echo strtolower(htmlspecialchars($user['name'])); ?>"
                                data-role="<?php echo htmlspecialchars($user['role']); ?>"
                                data-status="<?php echo htmlspecialchars($user['status']); ?>"
                                data-purok="<?php echo htmlspecialchars($user['purok_address']); ?>">
                                
                                <td class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">
                                    <?php echo htmlspecialchars($user['name']); ?>
                                </td>
                                <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-600">
                                    <?php echo htmlspecialchars($user['role']); ?>
                                </td>
                                <td class="px-6 py-4 whitespace-nowrap">
                                    <?php echo get_status_badge($user['status']); ?>
                                </td>
                                <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-600">
                                    <?php echo htmlspecialchars($user['purok_address']); ?>
                                </td>
                                <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-600">
                                    <?php echo htmlspecialchars($user['age']); ?>
                                </td>
                                <td class="px-6 py-4 whitespace-nowrap text-right text-sm font-medium">
                                    <?php if ($user['status'] === 'Pending'): ?>
                                        <a href="?action=verify&id=<?php echo urlencode($user['name']); ?>" 
                                           class="text-green-500 hover:text-green-700 mr-3 font-semibold">
                                            <i class="fas fa-check-circle mr-1"></i> Verify
                                        </a>
                                    <?php else: ?>
                                        <a href="?action=edit&id=<?php echo urlencode($user['name']); ?>" 
                                           class="text-indigo-600 hover:text-indigo-800 mr-3">
                                            <i class="fas fa-edit mr-1"></i> Edit
                                        </a>
                                    <?php endif; ?>
                                    <a href="?action=delete&id=<?php echo urlencode($user['name']); ?>" 
                                       class="text-red-600 hover:text-red-800">
                                        <i class="fas fa-trash-alt mr-1"></i> Delete
                                    </a>
                                </td>
                            </tr>
                            <?php endforeach; ?>
                            
                        </tbody>
                    </table>
                </div>
                
                <div class="mt-6 flex justify-between items-center text-sm text-gray-600">
                    <div id="result-count">Showing <?php echo count($users); ?> results</div>
                    <div class="flex space-x-1">
                        </div>
                </div>

            </div>
        </main>
    </div>

    <script>
        document.addEventListener('DOMContentLoaded', () => {
            const searchInput = document.getElementById('search-input');
            const roleFilter = document.getElementById('role-filter');
            const statusFilter = document.getElementById('status-filter');
            const purokFilter = document.getElementById('purok-filter');
            const userRows = document.querySelectorAll('.user-row');
            const resultCount = document.getElementById('result-count');

            const filterTable = () => {
                const search = searchInput.value.toLowerCase();
                const role = roleFilter.value;
                const status = statusFilter.value;
                const purok = purokFilter.value;
                let visibleCount = 0;

                userRows.forEach(row => {
                    const name = row.dataset.name;
                    const rowRole = row.dataset.role;
                    const rowStatus = row.dataset.status;
                    const rowPurok = row.dataset.purok;

                    // 1. Search Filter (Checks Name, Purok)
                    const searchMatch = name.includes(search) || rowPurok.toLowerCase().includes(search);

                    // 2. Role Filter
                    const roleMatch = role === '' || rowRole === role;

                    // 3. Status Filter
                    const statusMatch = status === '' || rowStatus === status;
                    
                    // 4. Purok Filter
                    const purokMatch = purok === '' || rowPurok === purok;

                    if (searchMatch && roleMatch && statusMatch && purokMatch) {
                        row.style.display = '';
                        visibleCount++;
                    } else {
                        row.style.display = 'none';
                    }
                });

                // Update result count display
                resultCount.textContent = `Showing ${visibleCount} result${visibleCount !== 1 ? 's' : ''}`;
            };

            // Attach event listeners
            searchInput.addEventListener('input', filterTable);
            roleFilter.addEventListener('change', filterTable);
            statusFilter.addEventListener('change', filterTable);
            purokFilter.addEventListener('change', filterTable);

            // Initial call to set the correct result count on load
            filterTable();
        });
    </script>
</body>
</html>