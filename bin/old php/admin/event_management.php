<?php
// admin_event_management.php
require_once('admin_sidebar.php');

// PHP Data Stubs (Enhanced data structure for filtering)
$events = [
    // Note: Used YYYY-MM-DD HH:MM:SS format internally for easier comparison in JS/PHP
    ['title' => 'Coastal Clean-Up Drive', 'datetime_raw' => '2025-12-20 09:00:00', 'datetime_display' => 'Dec 20, 9:00 AM', 'status' => 'Ongoing', 'registered' => 85],
    ['title' => 'Youth Sportsfest Tournament', 'datetime_raw' => '2026-03-10 14:00:00', 'datetime_display' => 'Mar 10, 2:00 PM', 'status' => 'Upcoming', 'registered' => 0],
    ['title' => 'Youth Leadership Summit', 'datetime_raw' => '2024-11-10 09:00:00', 'datetime_display' => 'Nov 10, 9:00 AM', 'status' => 'Completed', 'registered' => 120],
    ['title' => 'Barangay General Assembly', 'datetime_raw' => '2026-01-05 18:00:00', 'datetime_display' => 'Jan 5, 6:00 PM', 'status' => 'Upcoming', 'registered' => 45],
    ['title' => 'SK Budget Workshop', 'datetime_raw' => '2025-12-14 10:00:00', 'datetime_display' => 'Dec 14, 10:00 AM', 'status' => 'Completed', 'registered' => 15],
];

// PHP Logic for Modal Submission and Table Action Handling (Simulated)
$message = '';

if ($_SERVER["REQUEST_METHOD"] == "POST" && ($_POST['action'] ?? '') === 'add_event') {
    // 1. New Event Submission (From Modal)
    $event_title = htmlspecialchars($_POST['event_title'] ?? 'Unknown Event');
    $message = '<div class="bg-green-100 border border-green-400 text-green-700 px-4 py-3 rounded relative mb-4" role="alert"><strong class="font-bold">Success!</strong><span class="block sm:inline"> New event "' . $event_title . '" published.</span></div>';
    
} elseif ($_SERVER["REQUEST_METHOD"] == "GET" && isset($_GET['action']) && isset($_GET['event'])) {
    // 2. Table Row Action Handling (View Report, Edit, Cancel)
    $event_name = htmlspecialchars($_GET['event']);
    $action = htmlspecialchars($_GET['action']);
    
    if ($action === 'edit' || $action === 'view_report') {
        $message = '<div class="bg-blue-100 border border-blue-400 text-blue-700 px-4 py-3 rounded relative mb-4" role="alert"><strong class="font-bold">Action Successful.</strong><span class="block sm:inline"> Simulated action: You are now viewing the <strong>' . ucwords(str_replace('_', ' ', $action)) . '</strong> details for the event: <strong>' . $event_name . '</strong>.</span></div>';
    } elseif ($action === 'cancel') {
        $message = '<div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative mb-4" role="alert"><strong class="font-bold">Event Cancelled.</strong><span class="block sm:inline"> Simulated action: The event <strong>' . $event_name . '</strong> has been cancelled.</span></div>';
    }
    
    // To prevent the message from showing indefinitely after a refresh, redirect to clean the URL
    // header('Location: admin_event_management.php'); exit; // Uncomment in production to clear URL
}

function get_event_status_badge($status) {
    switch ($status) {
        case 'Ongoing':
            return '<span class="px-2 inline-flex text-xs leading-5 font-bold rounded-full bg-yellow-100 text-yellow-700">Ongoing</span>';
        case 'Upcoming':
            return '<span class="px-2 inline-flex text-xs leading-5 font-bold rounded-full bg-blue-100 text-blue-700">Upcoming</span>';
        case 'Completed':
            return '<span class="px-2 inline-flex text-xs leading-5 font-bold rounded-full bg-gray-200 text-gray-700">Completed</span>';
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
    <title>Event Management | SK Admin Portal</title>
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
        
        <?php render_admin_sidebar('events'); ?>

        <main class="flex-1 p-4 md:p-8 lg:p-10 overflow-y-auto lg-main-content">
            
            <div class="mb-8">
                <h1 class="text-4xl font-extrabold text-gray-900 mb-2 flex items-center">
                    <i class="fas fa-calendar-alt mr-3 text-green-600"></i> Event Management
                </h1>
                <p class="text-gray-500">Create, manage, and track attendance for all SK events.</p>
            </div>

            <?php echo $message; // Display PHP success/action message ?>
            
            <div class="bg-white p-6 md:p-8 rounded-xl shadow-2xl border border-gray-200">
                
                <div class="flex flex-col md:flex-row justify-between items-start md:items-center mb-6 space-y-4 md:space-y-0">
                    
                    <div class="flex flex-wrap gap-3 w-full md:w-auto">
                        
                        <input type="text" id="event-search" placeholder="Search event title..." 
                               class="w-full md:max-w-xs py-2 px-4 border border-gray-300 rounded-lg focus:ring-green-500 focus:border-green-500 transition duration-150 ease-in-out shadow-sm">
                        
                        <select id="status-filter" class="filter-select py-2 px-4 border border-gray-300 rounded-lg text-sm shadow-sm hover:border-green-400 cursor-pointer transition">
                            <option value="">Filter by Status</option>
                            <option value="Ongoing">Ongoing</option>
                            <option value="Upcoming">Upcoming</option>
                            <option value="Completed">Completed</option>
                        </select>

                        <select id="timeframe-filter" class="filter-select py-2 px-4 border border-gray-300 rounded-lg text-sm shadow-sm hover:border-green-400 cursor-pointer transition">
                            <option value="">Filter by Timeframe</option>
                            <option value="Past">Past Events</option>
                            <option value="Future">Future Events</option>
                            </select>
                    </div>

                    <button onclick="document.getElementById('addEventModal').classList.remove('hidden')" 
                            class="py-2 px-4 bg-green-600 text-white font-semibold rounded-lg hover:bg-green-700 transition duration-150 ease-in-out shadow-md hover:shadow-lg flex items-center w-full md:w-auto">
                        <i class="fas fa-plus mr-2"></i> Add New Event
                    </button>
                </div>
                
                <div class="overflow-x-auto">
                    <table class="min-w-full divide-y divide-gray-200">
                        <thead class="bg-green-50">
                            <tr>
                                <th scope="col" class="px-6 py-3 text-left text-xs font-semibold text-green-700 uppercase tracking-wider">Event Title</th>
                                <th scope="col" class="px-6 py-3 text-left text-xs font-semibold text-green-700 uppercase tracking-wider">Date & Time</th>
                                <th scope="col" class="px-6 py-3 text-left text-xs font-semibold text-green-700 uppercase tracking-wider">Status</th>
                                <th scope="col" class="px-6 py-3 text-center text-xs font-semibold text-green-700 uppercase tracking-wider">Registered Youth</th>
                                <th scope="col" class="px-6 py-3 text-right text-xs font-semibold text-green-700 uppercase tracking-wider">Actions</th>
                            </tr>
                        </thead>
                        <tbody id="event-table-body" class="bg-white divide-y divide-gray-100">
                            
                            <?php foreach ($events as $event): ?>
                            <tr class="event-row hover:bg-gray-50 transition duration-150" 
                                data-title="<?php echo strtolower(htmlspecialchars($event['title'])); ?>"
                                data-status="<?php echo htmlspecialchars($event['status']); ?>"
                                data-datetime="<?php echo htmlspecialchars($event['datetime_raw']); ?>">
                                
                                <td class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">
                                    <?php echo htmlspecialchars($event['title']); ?>
                                </td>
                                <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-600">
                                    <?php echo htmlspecialchars($event['datetime_display']); ?>
                                </td>
                                <td class="px-6 py-4 whitespace-nowrap">
                                    <?php echo get_event_status_badge($event['status']); ?>
                                </td>
                                <td class="px-6 py-4 whitespace-nowrap text-center text-sm text-blue-600 font-bold">
                                    <?php echo $event['registered']; ?>
                                </td>
                                <td class="px-6 py-4 whitespace-nowrap text-right text-sm font-medium">
                                    <?php if ($event['status'] === 'Completed'): ?>
                                        <a href="?action=view_report&event=<?php echo urlencode($event['title']); ?>" 
                                           class="text-blue-600 hover:text-blue-800 mr-3">
                                            <i class="fas fa-chart-bar mr-1"></i> View Report
                                        </a>
                                        <span class="text-gray-400">Archived</span>
                                    <?php else: ?>
                                        <a href="?action=view_report&event=<?php echo urlencode($event['title']); ?>" 
                                           class="text-blue-600 hover:text-blue-800 mr-3">
                                            <i class="fas fa-clipboard-list mr-1"></i> View Report
                                        </a>
                                        <a href="?action=edit&event=<?php echo urlencode($event['title']); ?>" 
                                           class="text-indigo-600 hover:text-indigo-800 mr-3">
                                            <i class="fas fa-edit mr-1"></i> Edit
                                        </a>
                                        <a href="?action=cancel&event=<?php echo urlencode($event['title']); ?>" 
                                           class="text-red-600 hover:text-red-800">
                                            <i class="fas fa-times-circle mr-1"></i> Cancel
                                        </a>
                                    <?php endif; ?>
                                </td>
                            </tr>
                            <?php endforeach; ?>
                            
                        </tbody>
                    </table>
                </div>
                
                <div class="mt-6 flex justify-between items-center text-sm text-gray-600">
                    <div id="result-count">Showing <?php echo count($events); ?> results</div>
                    <div>
                        </div>
                </div>

            </div>
        </main>

        <div id="addEventModal" class="fixed z-50 inset-0 overflow-y-auto hidden" aria-labelledby="modal-title" role="dialog" aria-modal="true">
            <div class="flex items-end justify-center min-h-screen pt-4 px-4 pb-20 text-center sm:block sm:p-0">
                <div class="fixed inset-0 bg-gray-900 bg-opacity-50 transition-opacity" aria-hidden="true"></div>
                <span class="hidden sm:inline-block sm:align-middle sm:h-screen" aria-hidden="true">&#8203;</span>
                <div class="inline-block align-bottom bg-white rounded-xl text-left overflow-hidden shadow-2xl transform transition-all sm:my-8 sm:align-middle sm:max-w-lg sm:w-full">
                    <form action="<?php echo htmlspecialchars($_SERVER['PHP_SELF']); ?>" method="POST">
                        <input type="hidden" name="action" value="add_event">
                        <div class="bg-white px-4 pt-5 pb-4 sm:p-6 sm:pb-4">
                            <h3 class="text-2xl leading-6 font-bold text-gray-900 mb-6 border-b pb-2 flex items-center" id="modal-title">
                                <i class="fas fa-calendar-plus mr-3 text-green-600"></i> Create New SK Event
                            </h3>
                            <div class="space-y-4">
                                <div>
                                    <label class="block text-sm font-semibold text-gray-700 mb-1">Event Title</label>
                                    <input type="text" name="event_title" required placeholder="e.g., Coastal Clean-Up Drive" class="w-full p-3 border border-gray-300 rounded-lg focus:ring-green-500 focus:border-green-500">
                                </div>
                                
                                <div class="grid grid-cols-2 gap-4">
                                    <div>
                                        <label class="block text-sm font-semibold text-gray-700 mb-1">Date and Time</label>
                                        <input type="datetime-local" name="event_datetime" required class="w-full p-3 border border-gray-300 rounded-lg focus:ring-green-500 focus:border-green-500">
                                    </div>
                                    <div>
                                        <label class="block text-sm font-semibold text-gray-700 mb-1">Maximum Slots (Optional)</label>
                                        <input type="number" name="event_slots" min="1" placeholder="100" class="w-full p-3 border border-gray-300 rounded-lg focus:ring-green-500 focus:border-green-500">
                                    </div>
                                </div>
                                
                                <div>
                                    <label class="block text-sm font-semibold text-gray-700 mb-1">Location</label>
                                    <input type="text" name="event_location" required placeholder="e.g., Barangay Hall" class="w-full p-3 border border-gray-300 rounded-lg focus:ring-green-500 focus:border-green-500">
                                </div>
                                
                                <div>
                                    <label class="block text-sm font-semibold text-gray-700 mb-1">Event Description</label>
                                    <textarea name="event_description" rows="3" placeholder="Details for the youth..." class="w-full p-3 border border-gray-300 rounded-lg focus:ring-green-500 focus:border-green-500"></textarea>
                                </div>
                            </div>
                        </div>
                        <div class="bg-gray-50 px-4 py-4 sm:px-6 sm:flex sm:flex-row-reverse rounded-b-xl">
                            <button type="submit" class="w-full inline-flex justify-center rounded-md border border-transparent shadow-sm px-4 py-2 bg-green-600 text-base font-semibold text-white hover:bg-green-700 sm:ml-3 sm:w-auto sm:text-sm transition duration-150">
                                <i class="fas fa-check-circle mr-2"></i> Publish Event
                            </button>
                            <button type="button" onclick="document.getElementById('addEventModal').classList.add('hidden')" class="mt-3 w-full inline-flex justify-center rounded-md border border-gray-300 shadow-sm px-4 py-2 bg-white text-base font-medium text-gray-700 hover:bg-gray-100 sm:mt-0 sm:ml-3 sm:w-auto sm:text-sm transition duration-150">
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
            const searchInput = document.getElementById('event-search');
            const statusFilter = document.getElementById('status-filter');
            const timeframeFilter = document.getElementById('timeframe-filter');
            const eventRows = document.querySelectorAll('.event-row');
            const resultCount = document.getElementById('result-count');

            const filterTable = () => {
                const search = searchInput.value.toLowerCase();
                const status = statusFilter.value;
                const timeframe = timeframeFilter.value;
                // Use a standard current date/time in the Philippines timezone, or just the client time for client-side comparison
                const now = new Date(); 
                let visibleCount = 0;

                eventRows.forEach(row => {
                    const title = row.dataset.title;
                    const rowStatus = row.dataset.status;
                    const rowDatetimeStr = row.dataset.datetime;
                    const rowDate = new Date(rowDatetimeStr);

                    // 1. Search Filter (Checks Title)
                    const searchMatch = title.includes(search);

                    // 2. Status Filter
                    const statusMatch = status === '' || rowStatus === status;
                    
                    // 3. Timeframe Filter
                    let timeframeMatch = true;
                    if (timeframe !== '') {
                        if (timeframe === 'Past') {
                            timeframeMatch = rowDate < now;
                        } else if (timeframe === 'Future') {
                            timeframeMatch = rowDate > now;
                        }
                    }

                    if (searchMatch && statusMatch && timeframeMatch) {
                        row.style.display = '';
                        visibleCount++;
                    } else {
                        row.style.display = 'none';
                    }
                });

                // Update result count display
                resultCount.textContent = `Showing ${visibleCount} event${visibleCount !== 1 ? 's' : ''}`;
            };

            // Attach event listeners
            searchInput.addEventListener('input', filterTable);
            statusFilter.addEventListener('change', filterTable);
            timeframeFilter.addEventListener('change', filterTable);
            
            // Initial call to set the correct result count on load
            filterTable();
        });
    </script>
</body>
</html>