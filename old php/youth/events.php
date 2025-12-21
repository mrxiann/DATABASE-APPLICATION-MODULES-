<?php
session_start();

// Check if user is logged in and is a youth
if (!isset($_SESSION['user_id']) || $_SESSION['role'] !== 'youth') {
    header("Location: login.php");
    exit();
}

require_once('youth_sidebar.php');

// PHP Data Stubs for Event Cards (Replace with database fetch logic)
$events = [
    [
        'id' => 1, 
        'title' => 'Coastal Clean-Up Drive 2026', 
        'date' => 'Jan 15, 2026', 
        'location' => 'Beachfront, Purok 3', 
        'type' => 'Volunteer Activity', 
        'status' => 'open',
        'slots' => '65 / 100',
        'border_color' => 'border-red-500', 
        'text_color' => 'text-red-600',
    ],
    [
        'id' => 2, 
        'title' => 'Youth Leadership Summit', 
        'date' => 'Dec 10, 2025', 
        'location' => 'Brgy Hall Auditorium', 
        'type' => 'Training/Seminar', 
        'status' => 'registered',
        'slots' => '110 / 120 Filled',
        'border_color' => 'border-green-500', 
        'text_color' => 'text-green-600',
    ],
];

// PHP structure for the JavaScript eventData variable
$js_event_data = [
    1 => [
        'title' => 'Coastal Clean-Up Drive 2026', 
        'type' => 'Volunteer Activity', 
        'date' => 'Jan 15, 2026', 
        'time' => '7:00 AM - 12:00 PM', 
        'location' => 'Beachfront, Purok 3', 
        'slots' => '65 / 100', 
        'req' => 'Long sleeves, Hat, Water bottle', 
        'description' => 'Join us for a morning of environmental awareness and community service.'
    ],
    2 => [
        'title' => 'Youth Leadership Summit', 
        'type' => 'Training/Seminar', 
        'date' => 'Dec 10, 2025', 
        'time' => '9:00 AM - 4:00 PM', 
        'location' => 'Brgy Hall Auditorium', 
        'slots' => '110 / 120 Filled', 
        'req' => 'Pre-registration required, Notebook & Pen', 
        'description' => 'A comprehensive summit designed to hone leadership skills.'
    ]
];
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Events & Registration | SK Portal</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body class="bg-gray-50">
    <div class="flex h-screen">
        <?php render_youth_sidebar('events'); ?>

        <main class="ml-64 flex-1 p-8 overflow-y-auto">
            <h2 class="text-3xl font-bold text-gray-800 mb-6 border-b pb-2">Upcoming Events & Registration</h2>
            
            <div class="bg-white p-4 rounded-xl shadow-md mb-6 flex space-x-4 items-center">
                 <input type="text" placeholder="Search event title or location..." class="py-2 px-4 border rounded-lg flex-1 max-w-sm">
                 <select class="py-2 px-4 border rounded-lg text-sm">
                    <option value="">Filter by Status</option>
                    <option value="open">Open for Registration</option>
                    <option value="registered">My Registered Events</option>
                    <option value="upcoming">Upcoming</option>
                 </select>
                 <select class="py-2 px-4 border rounded-lg text-sm">
                    <option value="">Filter by Type</option>
                    <option value="volunteer">Volunteer Activity</option>
                    <option value="seminar">Training/Seminar</option>
                    <option value="sports">Sports/Recreational</option>
                 </select>
                 <button class="py-2 px-4 bg-indigo-500 text-white font-medium rounded-lg hover:bg-indigo-600 transition text-sm">Apply Filters</button>
            </div>

            <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-6">
                
                <?php foreach ($events as $event): ?>
                    <div class="bg-white rounded-xl shadow-lg overflow-hidden border-t-4 <?php echo htmlspecialchars($event['border_color']); ?>">
                        <div class="p-4">
                            <p class="text-xs font-semibold <?php echo htmlspecialchars($event['text_color']); ?> mb-1 uppercase"><?php echo htmlspecialchars($event['type']); ?></p>
                            <h4 class="text-xl font-bold text-gray-900 line-clamp-2"><?php echo htmlspecialchars($event['title']); ?></h4>
                            <p class="text-sm text-gray-600 mt-2"><i class="fas fa-calendar-alt mr-1"></i> <?php echo htmlspecialchars($event['date']); ?></p>
                            <p class="text-sm text-gray-600"><i class="fas fa-map-marker-alt mr-1"></i> <?php echo htmlspecialchars($event['location']); ?></p>
                            
                            <?php if ($event['status'] === 'registered'): ?>
                                <p class="text-xs text-green-500 mt-2 font-bold">You are REGISTERED!</p>
                            <?php else: ?>
                                <p class="text-xs text-gray-500 mt-2">Slots left: <?php echo htmlspecialchars($event['slots']); ?></p>
                            <?php endif; ?>
                        </div>
                        <div class="p-4 border-t">
                            <?php 
                            $is_registered = $event['status'] === 'registered' ? 'true' : 'false';
                            $button_text = $event['status'] === 'open' ? '<i class="fas fa-user-plus mr-2"></i> Register Now' : 
                                           ($event['status'] === 'registered' ? '<i class="fas fa-check-circle mr-2"></i> View Details' : 'Registration Closed');
                            $button_class = $event['status'] === 'open' ? 'bg-indigo-600 hover:bg-indigo-700' : 
                                            ($event['status'] === 'registered' ? 'bg-green-600 hover:bg-green-700' : 'bg-gray-400 cursor-not-allowed');
                            $button_disabled = $event['status'] === 'closed' ? 'disabled' : '';
                            ?>
                            <button onclick="openEventModal(<?php echo $event['id']; ?>, <?php echo $is_registered; ?>)" 
                                    class="w-full py-2 text-white font-medium rounded-lg transition <?php echo $button_class; ?>"
                                    <?php echo $button_disabled; ?>>
                                <?php echo $button_text; ?>
                            </button>
                        </div>
                    </div>
                <?php endforeach; ?>

            </div>
        </main>
    </div>
    
    <div id="eventModal" class="fixed z-10 inset-0 overflow-y-auto hidden" aria-labelledby="modal-title" role="dialog" aria-modal="true">
        <div class="flex items-center justify-center min-h-screen pt-4 px-4 pb-20 text-center sm:block sm:p-0">
            <div class="fixed inset-0 bg-gray-500 bg-opacity-75 transition-opacity" aria-hidden="true"></div>
            <span class="hidden sm:inline-block sm:align-middle sm:h-screen" aria-hidden="true">&#8203;</span>
            <div class="inline-block align-bottom bg-white rounded-lg text-left overflow-hidden shadow-xl transform transition-all sm:my-8 sm:align-middle sm:max-w-xl sm:w-full">
                <div class="bg-white px-4 pt-5 pb-4 sm:p-6 sm:pb-4">
                    <div class="sm:flex sm:items-start">
                        <div class="mt-3 text-center sm:mt-0 sm:ml-4 sm:text-left w-full">
                            <h3 class="text-2xl leading-6 font-bold text-gray-900" id="modal-title"></h3>
                            <div class="mt-2 space-y-3">
                                <div class="flex items-center text-sm text-gray-700">
                                    <i class="fas fa-tag mr-2 text-indigo-500"></i> <span id="modal-type" class="font-medium text-indigo-600"></span>
                                </div>
                                <div class="flex items-center text-sm text-gray-700">
                                    <i class="fas fa-clock mr-2 text-blue-500"></i> <span id="modal-datetime"></span>
                                </div>
                                <div class="flex items-center text-sm text-gray-700">
                                    <i class="fas fa-map-marker-alt mr-2 text-red-500"></i> <span id="modal-location"></span>
                                </div>
                                <div class="flex items-center text-sm text-gray-700">
                                    <i class="fas fa-users mr-2 text-green-500"></i> <span id="modal-slots"></span>
                                </div>
                                <div class="flex items-center text-sm text-gray-700">
                                    <i class="fas fa-info-circle mr-2 text-yellow-500"></i> <span id="modal-requirements"></span>
                                </div>
                                
                                <p class="text-sm text-gray-500 pt-4 border-t" id="modal-description"></p>

                                <div id="modal-status-section" class="mt-6 p-4 rounded-lg text-center"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="bg-gray-50 px-4 py-3 sm:flex sm:flex-row-reverse sm:px-6">
                    <button type="button" id="modal-action-button" onclick="handleEventAction()" class="w-full inline-flex justify-center rounded-md border border-transparent shadow-sm px-4 py-2 bg-indigo-600 text-base font-medium text-white hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500 sm:ml-3 sm:w-auto sm:text-sm"></button>
                    <button type="button" onclick="document.getElementById('eventModal').classList.add('hidden')" class="mt-3 w-full inline-flex justify-center rounded-md border border-gray-300 shadow-sm px-4 py-2 bg-white text-base font-medium text-gray-700 hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500 sm:mt-0 sm:w-auto sm:text-sm">
                        Close
                    </button>
                </div>
            </div>
        </div>
    </div>

    <script>
        // PHP array converted to JavaScript object for modal content lookup
        const eventData = <?php echo json_encode($js_event_data, JSON_PRETTY_PRINT); ?>;
        let currentEventId = null;
        let isUserRegistered = false;

        function openEventModal(eventId, registered) {
            currentEventId = eventId;
            isUserRegistered = registered;
            const event = eventData[eventId];
            if (!event) return;

            // Update Modal Content
            document.getElementById('modal-title').textContent = event.title;
            document.getElementById('modal-type').textContent = event.type;
            document.getElementById('modal-datetime').textContent = `${event.date} @ ${event.time}`;
            document.getElementById('modal-location').textContent = event.location;
            document.getElementById('modal-slots').textContent = `Slots left: ${event.slots}`;
            document.getElementById('modal-requirements').textContent = `Requires: ${event.req}`;
            document.getElementById('modal-description').textContent = event.description;
            
            const statusSection = document.getElementById('modal-status-section');
            const actionButton = document.getElementById('modal-action-button');
            
            // Logic for Registered vs. Not Registered
            if (isUserRegistered) {
                statusSection.className = 'mt-6 p-4 rounded-lg text-center bg-green-100 border border-green-400';
                statusSection.innerHTML = '<i class="fas fa-check-circle text-green-600 text-xl mr-2"></i> <span class="font-bold text-green-700">Registration Confirmed!</span> <p class="text-sm text-green-700 mt-1">Please arrive on time and bring your QR ID for attendance.</p>';
                actionButton.textContent = "Cancel Registration";
                actionButton.className = 'w-full inline-flex justify-center rounded-md border border-transparent shadow-sm px-4 py-2 bg-red-600 text-base font-medium text-white hover:bg-red-700 sm:ml-3 sm:w-auto sm:text-sm';
            } else {
                statusSection.className = 'mt-6 p-4 rounded-lg text-center bg-yellow-100 border border-yellow-400';
                statusSection.innerHTML = '<i class="fas fa-exclamation-triangle text-yellow-600 text-xl mr-2"></i> <span class="font-bold text-yellow-700">You are not yet registered.</span> <p class="text-sm text-yellow-700 mt-1">Click the button below to secure your slot.</p>';
                actionButton.textContent = "Register Now";
                actionButton.className = 'w-full inline-flex justify-center rounded-md border border-transparent shadow-sm px-4 py-2 bg-indigo-600 text-base font-medium text-white hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500 sm:ml-3 sm:w-auto sm:text-sm';
            }

            document.getElementById('eventModal').classList.remove('hidden');
        }

        function handleEventAction() {
            if (currentEventId === null) return;
            // In a real application, this would be an AJAX call to a PHP registration endpoint
            alert((isUserRegistered ? 'Cancelling' : 'Registering for') + ' event ID ' + currentEventId);
            document.getElementById('eventModal').classList.add('hidden');
            // window.location.reload(); // Reload to reflect registration change
        }
    </script>
</body>
</html>