<?php
// admin_opportunities.php
require_once('admin_sidebar.php');

// PHP Data Stubs (Enhanced data structure)
$opportunities = [
    ['title' => 'SK Admin Assistant (P/T)', 'type' => 'Job', 'status' => 'Open', 'applicants' => 12, 'deadline' => '2026-02-28'],
    ['title' => 'Barangay Health Volunteer', 'type' => 'Volunteer', 'status' => 'Ongoing', 'applicants' => 35, 'deadline' => 'N/A'],
    ['title' => 'SK Communications Intern', 'type' => 'Internship', 'status' => 'Closed', 'applicants' => 8, 'deadline' => '2025-12-15'],
    ['title' => 'Youth Entrepreneurship Workshop', 'type' => 'Seminar', 'status' => 'Open', 'applicants' => 50, 'deadline' => '2026-05-01'],
];

// Dynamically collect unique types for the filter
$opportunity_types = array_unique(array_column($opportunities, 'type'));
sort($opportunity_types);

// PHP Logic for Modal Submission and Table Action Handling (Simulated)
$message = '';

if ($_SERVER["REQUEST_METHOD"] == "POST" && ($_POST['action'] ?? '') === 'add_opportunity') {
    // 1. New Opportunity Submission (From Modal)
    $opp_title = htmlspecialchars($_POST['opp_title'] ?? 'Unknown Opportunity');
    $message = '<div class="bg-green-100 border border-green-400 text-green-700 px-4 py-3 rounded relative mb-4" role="alert"><strong class="font-bold">Success!</strong><span class="block sm:inline"> New opportunity "' . $opp_title . '" published.</span></div>';
    
} elseif ($_SERVER["REQUEST_METHOD"] == "GET" && isset($_GET['action']) && isset($_GET['opp'])) {
    // 2. Table Row Action Handling (View Applications, Edit, Close)
    $opp_name = htmlspecialchars($_GET['opp']);
    $action = htmlspecialchars($_GET['action']);
    
    if ($action === 'view_apps') {
        $message = '<div class="bg-blue-100 border border-blue-400 text-blue-700 px-4 py-3 rounded relative mb-4" role="alert"><strong class="font-bold">Action Successful.</strong><span class="block sm:inline"> Simulated action: You are now viewing the applicants for <strong>' . $opp_name . '</strong>.</span></div>';
    } elseif ($action === 'edit') {
        $message = '<div class="bg-indigo-100 border border-indigo-400 text-indigo-700 px-4 py-3 rounded relative mb-4" role="alert"><strong class="font-bold">Action Successful.</strong><span class="block sm:inline"> Simulated action: You are now editing the details for <strong>' . $opp_name . '</strong>.</span></div>';
    } elseif ($action === 'close') {
        $message = '<div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative mb-4" role="alert"><strong class="font-bold">Opportunity Closed.</strong><span class="block sm:inline"> Simulated action: The opportunity <strong>' . $opp_name . '</strong> is now closed for applications.</span></div>';
    }
    
    // In a real application, you would handle the database update here.
    // To prevent the message from showing indefinitely after a refresh, uncomment the line below:
    // header('Location: admin_opportunities.php'); exit; 
}

function get_opp_status_badge($status) {
    switch ($status) {
        case 'Open':
        case 'Ongoing':
            return '<span class="px-2 inline-flex text-xs leading-5 font-bold rounded-full bg-green-100 text-green-700">' . htmlspecialchars($status) . '</span>';
        case 'Closed':
            return '<span class="px-2 inline-flex text-xs leading-5 font-bold rounded-full bg-red-100 text-red-700">Closed</span>';
        default:
            return '<span class="px-2 inline-flex text-xs leading-5 font-bold rounded-full bg-gray-200 text-gray-700">' . htmlspecialchars($status) . '</span>';
    }
}
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Opportunities Management | SK Admin Portal</title>
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
        
        <?php render_admin_sidebar('opportunities'); ?>

        <main class="flex-1 p-4 md:p-8 lg:p-10 overflow-y-auto lg-main-content">
            
            <div class="mb-8">
                <h1 class="text-4xl font-extrabold text-gray-900 mb-2 flex items-center">
                    <i class="fas fa-briefcase mr-3 text-indigo-600"></i> Opportunities Management
                </h1>
                <p class="text-gray-500">Post and manage job, OJT, and volunteer openings for the youth.</p>
            </div>

            <?php echo $message; // Display PHP success/action message ?>

            <div class="bg-white p-6 md:p-8 rounded-xl shadow-2xl border border-gray-200">
                
                <div class="flex flex-col md:flex-row justify-between items-start md:items-center mb-6 space-y-4 md:space-y-0">
                    
                    <div class="flex flex-wrap gap-3 w-full md:w-auto">
                        
                        <input type="text" id="opp-search" placeholder="Search title or type..." 
                               class="w-full md:max-w-xs py-2 px-4 border border-gray-300 rounded-lg focus:ring-indigo-500 focus:border-indigo-500 transition duration-150 ease-in-out shadow-sm">
                        
                        <select id="type-filter" class="filter-select py-2 px-4 border border-gray-300 rounded-lg text-sm shadow-sm hover:border-indigo-400 cursor-pointer transition">
                            <option value="">Filter by Type</option>
                            <?php foreach ($opportunity_types as $type): ?>
                                <option value="<?php echo htmlspecialchars($type); ?>"><?php echo htmlspecialchars($type); ?></option>
                            <?php endforeach; ?>
                        </select>

                        <select id="status-filter" class="filter-select py-2 px-4 border border-gray-300 rounded-lg text-sm shadow-sm hover:border-indigo-400 cursor-pointer transition">
                            <option value="">Filter by Status</option>
                            <option value="Open">Open</option>
                            <option value="Ongoing">Ongoing</option>
                            <option value="Closed">Closed</option>
                        </select>
                    </div>

                    <button onclick="document.getElementById('addOpportunityModal').classList.remove('hidden')" 
                            class="py-2 px-4 bg-indigo-600 text-white font-semibold rounded-lg hover:bg-indigo-700 transition duration-150 ease-in-out shadow-md hover:shadow-lg flex items-center w-full md:w-auto">
                        <i class="fas fa-plus mr-2"></i> Post New Opportunity
                    </button>
                </div>
                
                <div class="overflow-x-auto">
                    <table class="min-w-full divide-y divide-gray-200">
                        <thead class="bg-indigo-50">
                            <tr>
                                <th scope="col" class="px-6 py-3 text-left text-xs font-semibold text-indigo-700 uppercase tracking-wider">Title</th>
                                <th scope="col" class="px-6 py-3 text-left text-xs font-semibold text-indigo-700 uppercase tracking-wider">Type</th>
                                <th scope="col" class="px-6 py-3 text-left text-xs font-semibold text-indigo-700 uppercase tracking-wider">Status</th>
                                <th scope="col" class="px-6 py-3 text-center text-xs font-semibold text-indigo-700 uppercase tracking-wider">Applicants</th>
                                <th scope="col" class="px-6 py-3 text-left text-xs font-semibold text-indigo-700 uppercase tracking-wider">Deadline</th>
                                <th scope="col" class="px-6 py-3 text-right text-xs font-semibold text-indigo-700 uppercase tracking-wider">Actions</th>
                            </tr>
                        </thead>
                        <tbody id="opp-table-body" class="bg-white divide-y divide-gray-100">
                            
                            <?php foreach ($opportunities as $opp): ?>
                            <tr class="opp-row hover:bg-gray-50 transition duration-150"
                                data-title="<?php echo strtolower(htmlspecialchars($opp['title'])); ?>"
                                data-type="<?php echo htmlspecialchars($opp['type']); ?>"
                                data-status="<?php echo htmlspecialchars($opp['status']); ?>"
                                data-deadline="<?php echo htmlspecialchars($opp['deadline']); ?>">

                                <td class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900"><?php echo htmlspecialchars($opp['title']); ?></td>
                                <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-600"><?php echo htmlspecialchars($opp['type']); ?></td>
                                <td class="px-6 py-4 whitespace-nowrap"><?php echo get_opp_status_badge($opp['status']); ?></td>
                                <td class="px-6 py-4 whitespace-nowrap text-center text-sm text-blue-600 font-bold"><?php echo $opp['applicants']; ?></td>
                                <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-600">
                                    <?php 
                                    $deadline_text = htmlspecialchars($opp['deadline']);
                                    // Check if status is Open AND deadline is not N/A AND the deadline is in the past
                                    if ($opp['deadline'] !== 'N/A' && $opp['status'] === 'Open' && strtotime($opp['deadline']) < time()) {
                                        echo '<span class="text-red-600 font-semibold">' . $deadline_text . ' (Past)</span>';
                                    } else {
                                        echo $deadline_text;
                                    }
                                    ?>
                                </td>
                                <td class="px-6 py-4 whitespace-nowrap text-right text-sm font-medium">
                                    <a href="?action=view_apps&opp=<?php echo urlencode($opp['title']); ?>" 
                                       class="text-blue-600 hover:text-blue-800 mr-3">
                                        <i class="fas fa-users-viewfinder mr-1"></i> View Applicants
                                    </a>
                                    <?php if ($opp['status'] !== 'Closed'): ?>
                                        <a href="?action=edit&opp=<?php echo urlencode($opp['title']); ?>" 
                                           class="text-indigo-600 hover:text-indigo-800 mr-3">
                                            <i class="fas fa-edit mr-1"></i> Edit
                                        </a>
                                        <a href="?action=close&opp=<?php echo urlencode($opp['title']); ?>" 
                                           class="text-red-600 hover:text-red-800">
                                            <i class="fas fa-times-circle mr-1"></i> Close
                                        </a>
                                    <?php else: ?>
                                        <span class="text-gray-400">Archived</span>
                                    <?php endif; ?>
                                </td>
                            </tr>
                            <?php endforeach; ?>
                            
                        </tbody>
                    </table>
                </div>
                
                <div class="mt-6 flex justify-between items-center text-sm text-gray-600">
                    <div id="result-count">Showing <?php echo count($opportunities); ?> results</div>
                    <div>
                        </div>
                </div>

            </div>
        </main>

        <div id="addOpportunityModal" class="fixed z-50 inset-0 overflow-y-auto hidden" aria-labelledby="modal-title" role="dialog" aria-modal="true">
            <div class="flex items-end justify-center min-h-screen pt-4 px-4 pb-20 text-center sm:block sm:p-0">
                <div class="fixed inset-0 bg-gray-900 bg-opacity-50 transition-opacity" aria-hidden="true"></div>
                <span class="hidden sm:inline-block sm:align-middle sm:h-screen" aria-hidden="true">&#8203;</span>
                <div class="inline-block align-bottom bg-white rounded-xl text-left overflow-hidden shadow-2xl transform transition-all sm:my-8 sm:align-middle sm:max-w-lg sm:w-full">
                    <form action="<?php echo htmlspecialchars($_SERVER['PHP_SELF']); ?>" method="POST">
                        <input type="hidden" name="action" value="add_opportunity">
                        <div class="bg-white px-4 pt-5 pb-4 sm:p-6 sm:pb-4">
                            <h3 class="text-2xl leading-6 font-bold text-gray-900 mb-6 border-b pb-2 flex items-center" id="modal-title">
                                <i class="fas fa-clipboard-list mr-3 text-indigo-600"></i> Post New Opportunity
                            </h3>
                            <div class="space-y-4">
                                <div>
                                    <label class="block text-sm font-semibold text-gray-700 mb-1">Opportunity Title</label>
                                    <input type="text" name="opp_title" required placeholder="e.g., Summer Youth Intern" class="w-full p-3 border border-gray-300 rounded-lg focus:ring-indigo-500 focus:border-indigo-500">
                                </div>
                                
                                <div class="grid grid-cols-2 gap-4">
                                    <div>
                                        <label class="block text-sm font-semibold text-gray-700 mb-1">Type of Opportunity</label>
                                        <select name="opp_type" required class="w-full p-3 border border-gray-300 rounded-lg focus:ring-indigo-500 focus:border-indigo-500">
                                            <option value="Job">Job</option>
                                            <option value="Volunteer">Volunteer</option>
                                            <option value="Internship">Internship</option>
                                            <option value="Seminar">Seminar</option>
                                        </select>
                                    </div>
                                    <div>
                                        <label class="block text-sm font-semibold text-gray-700 mb-1">Application Deadline</label>
                                        <input type="date" name="opp_deadline" class="w-full p-3 border border-gray-300 rounded-lg focus:ring-indigo-500 focus:border-indigo-500">
                                        <p class="text-xs text-gray-500 mt-1">Leave blank for Ongoing/N/A.</p>
                                    </div>
                                </div>
                                
                                <div>
                                    <label class="block text-sm font-semibold text-gray-700 mb-1">Opportunity Description</label>
                                    <textarea name="opp_description" rows="3" required placeholder="Detailed requirements and responsibilities..." class="w-full p-3 border border-gray-300 rounded-lg focus:ring-indigo-500 focus:border-indigo-500"></textarea>
                                </div>
                                
                                <div>
                                    <label class="block text-sm font-semibold text-gray-700 mb-1">Required Number of Slots</label>
                                    <input type="number" name="opp_slots" min="1" placeholder="5" class="w-full p-3 border border-gray-300 rounded-lg focus:ring-indigo-500 focus:border-indigo-500">
                                </div>
                                
                            </div>
                        </div>
                        <div class="bg-gray-50 px-4 py-4 sm:px-6 sm:flex sm:flex-row-reverse rounded-b-xl">
                            <button type="submit" class="w-full inline-flex justify-center rounded-md border border-transparent shadow-sm px-4 py-2 bg-indigo-600 text-base font-semibold text-white hover:bg-indigo-700 sm:ml-3 sm:w-auto sm:text-sm transition duration-150">
                                <i class="fas fa-upload mr-2"></i> Publish Opportunity
                            </button>
                            <button type="button" onclick="document.getElementById('addOpportunityModal').classList.add('hidden')" class="mt-3 w-full inline-flex justify-center rounded-md border border-gray-300 shadow-sm px-4 py-2 bg-white text-base font-medium text-gray-700 hover:bg-gray-100 sm:mt-0 sm:ml-3 sm:w-auto sm:text-sm transition duration-150">
                                Cancel
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script>
        document.addEventListener('DOMContentLoaded', () => {
            const searchInput = document.getElementById('opp-search');
            const typeFilter = document.getElementById('type-filter');
            const statusFilter = document.getElementById('status-filter');
            const oppRows = document.querySelectorAll('.opp-row');
            const resultCount = document.getElementById('result-count');

            const filterTable = () => {
                const search = searchInput.value.toLowerCase();
                const type = typeFilter.value;
                const status = statusFilter.value;
                let visibleCount = 0;

                oppRows.forEach(row => {
                    const title = row.dataset.title;
                    const rowType = row.dataset.type;
                    const rowStatus = row.dataset.status;

                    // 1. Search Filter (Checks Title)
                    const searchMatch = title.includes(search);

                    // 2. Type Filter
                    const typeMatch = type === '' || rowType === type;

                    // 3. Status Filter
                    const statusMatch = status === '' || rowStatus === status;
                    
                    if (searchMatch && typeMatch && statusMatch) {
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
            typeFilter.addEventListener('change', filterTable);
            statusFilter.addEventListener('change', filterTable);
            
            // Initial call to set the correct result count on load
            filterTable();
        });
    </script>
</body>
</html>