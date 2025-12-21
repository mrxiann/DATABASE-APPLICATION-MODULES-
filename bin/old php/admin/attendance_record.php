<?php
// admin_attendance_record.php
require_once('admin_sidebar.php');

// --- 1. PHP Data Stubs ---
// NOTE: In a real application, these would be fetched from a database.
$events = [
    'EVENT-001' => ['title' => 'Coastal Clean-Up Drive', 'date' => 'Feb 15, 2026'],
    'EVENT-002' => ['title' => 'Youth Sportsfest Finals', 'date' => 'Mar 10, 2026'],
    'EVENT-003' => ['title' => 'SK Leadership Summit', 'date' => 'Apr 22, 2026'],
];

// Attendance records tied to events
$all_attendance_data = [
    'EVENT-001' => [
        'SK-YOUTH-101' => ['name' => 'Juan D. Cruz', 'timeIn' => '09:00 AM', 'timeOut' => '12:00 PM', 'status' => 'Out'],
        'SK-YOUTH-102' => ['name' => 'Maria S. Reyes', 'timeIn' => '09:05 AM', 'timeOut' => 'N/A', 'status' => 'In'],
        'SK-YOUTH-103' => ['name' => 'Benito M. Diaz', 'timeIn' => '08:55 AM', 'timeOut' => '04:00 PM', 'status' => 'Out'],
    ],
    'EVENT-002' => [
        'SK-YOUTH-201' => ['name' => 'Crispin G. Diaz', 'timeIn' => '02:00 PM', 'timeOut' => 'N/A', 'status' => 'In'],
        'SK-YOUTH-202' => ['name' => 'Francis B. Pangan', 'timeIn' => '02:15 PM', 'timeOut' => '06:00 PM', 'status' => 'Out'],
    ],
    'EVENT-003' => [], // Starting empty
];

// Master list of all registered youth for validation
$dummy_user_list = [
    'SK-YOUTH-101' => 'Juan D. Cruz',
    'SK-YOUTH-102' => 'Maria S. Reyes',
    'SK-YOUTH-103' => 'Benito M. Diaz',
    'SK-YOUTH-201' => 'Crispin G. Diaz',
    'SK-YOUTH-202' => 'Francis B. Pangan',
    'SK-YOUTH-301' => 'Elena R. Sotto',
    'SK-YOUTH-302' => 'Gregorio H. Bato',
    'SK-YOUTH-303' => 'Jessica L. Ochoa',
];

// --- 2. Event Selection Logic ---
// Get event_id from URL, or default to the first event
$selected_event_id = $_GET['event_id'] ?? array_key_first($events);

// Validate and set current event data
if (!isset($events[$selected_event_id])) {
    $selected_event_id = array_key_first($events);
}

$current_event = $events[$selected_event_id];
$current_records = $all_attendance_data[$selected_event_id] ?? [];

// PHP to prepare the JS arrays for the currently selected event
$js_initial_records = json_encode($current_records);
$js_dummy_user_list = json_encode($dummy_user_list);
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Live Attendance Scanner | SK Admin Portal</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        @media (min-width: 1024px) {
            .lg-main-content {
                margin-left: 16rem; /* ml-64 */
            }
        }
        .scanner-placeholder {
            min-height: 300px;
            background-color: #000;
            border: 4px solid #4f46e5;
            display: flex;
            align-items: center;
            justify-content: center;
            color: white;
            font-weight: bold;
            font-size: 1.25rem;
            border-radius: 0.5rem;
        }
    </style>
</head>
<body class="bg-gray-100 font-sans">
    <div class="flex min-h-screen">
        
        <?php render_admin_sidebar('attendance'); ?>

        <main class="flex-1 p-4 md:p-8 lg:p-10 overflow-y-auto lg-main-content">
            
            <div class="mb-6 border-b pb-4 flex flex-col md:flex-row justify-between items-start md:items-center">
                <h1 class="text-4xl font-extrabold text-gray-900 mb-2 flex items-center">
                    <i class="fas fa-fingerprint mr-3 text-indigo-600"></i> Live Attendance Scanner
                </h1>
                
                <div class="flex items-center space-x-3 w-full md:w-auto mt-2 md:mt-0">
                    <label for="event-select" class="text-base font-medium text-gray-700 whitespace-nowrap">Event:</label>
                    <select id="event-select" 
                            onchange="window.location.href='?event_id=' + this.value" 
                            class="py-2 px-3 border border-gray-300 rounded-lg shadow-sm focus:ring-indigo-500 focus:border-indigo-500 w-full md:w-auto cursor-pointer">
                        <?php foreach ($events as $id => $event): ?>
                            <option value="<?php echo $id; ?>" 
                                    <?php echo $id === $selected_event_id ? 'selected' : ''; ?>>
                                <?php echo htmlspecialchars($event['title'] . ' (' . $event['date'] . ')'); ?>
                            </option>
                        <?php endforeach; ?>
                    </select>
                </div>
            </div>

            <p class="text-gray-600 mb-8">
                Currently recording attendance for: **<?php echo htmlspecialchars($current_event['title']); ?>** (<?php echo htmlspecialchars($current_event['date']); ?>).
            </p>

            <div class="grid grid-cols-1 lg:grid-cols-3 gap-8">
                
                <div class="lg:col-span-1 bg-white p-6 rounded-xl shadow-2xl border border-gray-200 lg:sticky lg:top-8 h-fit">
                    <h3 class="text-xl font-semibold mb-4 text-center border-b pb-2"><i class="fas fa-qrcode mr-2 text-indigo-600"></i> QR Code Scanner</h3>
                    
                    <div class="scanner-placeholder">
                        <p>Placeholder: Live Camera Feed</p>
                    </div>

                    <div id="scanFeedback" class="hidden mt-4 p-4 font-medium rounded-lg transition duration-300 ease-in-out">
                    </div>

                    <div class="mt-6">
                        <h4 class="text-lg font-medium text-gray-700 mb-2">Manual Youth ID Input</h4>
                        <div class="flex">
                            <input type="text" id="manualCodeInput" placeholder="Enter Youth ID (e.g., SK-YOUTH-301)" 
                                   class="flex-1 p-3 border border-gray-300 rounded-l-lg focus:ring-indigo-500 focus:border-indigo-500">
                            <button onclick="scanQrCode(document.getElementById('manualCodeInput').value)" 
                                    class="py-3 px-4 bg-indigo-600 text-white font-semibold rounded-r-lg hover:bg-indigo-700 transition shadow-md">
                                <i class="fas fa-camera mr-1"></i> Scan
                            </button>
                        </div>
                    </div>
                </div>

                <div class="lg:col-span-2 bg-white p-6 rounded-xl shadow-2xl border border-gray-200">
                    <h3 class="text-xl font-semibold mb-4 border-b pb-2"><i class="fas fa-list-ul mr-2 text-indigo-600"></i> Attendance Log</h3>
                    
                    <div class="flex justify-between items-center mb-4 flex-wrap gap-2">
                        <div class="text-lg font-bold">
                            <span class="text-green-600 mr-4"><i class="fas fa-arrow-alt-circle-right"></i> In: <span id="inCount">0</span></span>
                            <span class="text-red-600"><i class="fas fa-arrow-alt-circle-left"></i> Out: <span id="outCount">0</span></span>
                        </div>
                        <button onclick="exportAttendanceData()"
                                class="py-2 px-4 bg-gray-200 text-gray-700 text-sm font-semibold rounded-lg hover:bg-gray-300 transition shadow-sm">
                            <i class="fas fa-file-csv mr-1"></i> Export Data (CSV)
                        </button>
                    </div>

                    <div class="overflow-x-auto">
                        <table class="min-w-full divide-y divide-gray-200">
                            <thead class="bg-gray-50">
                                <tr>
                                    <th class="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">Youth ID</th>
                                    <th class="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">Name</th>
                                    <th class="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">Time In</th>
                                    <th class="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">Time Out</th>
                                    <th class="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">Status</th>
                                </tr>
                            </thead>
                            <tbody id="attendanceLogBody" class="bg-white divide-y divide-gray-100">
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </main>
    </div>

    <script>
        // Data structure to hold attendance records (PHP data injected here)
        let attendanceRecords = <?php echo $js_initial_records; ?>;
        
        // Dummy users list for new check-ins
        const dummyUserList = <?php echo $js_dummy_user_list; ?>;

        /**
         * Renders the attendance log table based on the current attendanceRecords state.
         */
        function renderAttendanceLog() {
            const tbody = document.getElementById('attendanceLogBody');
            tbody.innerHTML = ''; // Clear existing rows
            let inCount = 0;
            let outCount = 0;

            // Sort records: put 'In' users first, then sort by Time In
            const sortedRecords = Object.entries(attendanceRecords).sort(([idA, recA], [idB, recB]) => {
                if (recA.status === 'In' && recB.status !== 'In') return -1;
                if (recA.status !== 'In' && recB.status === 'In') return 1;
                // Simple string comparison for time
                return recB.timeIn.localeCompare(recA.timeIn); 
            });

            sortedRecords.forEach(([id, record]) => {
                const statusBadge = record.status === 'In' 
                    ? `<span class="px-2 inline-flex text-xs leading-5 font-bold rounded-full bg-green-100 text-green-800">CHECKED IN</span>`
                    : `<span class="px-2 inline-flex text-xs leading-5 font-bold rounded-full bg-red-100 text-red-800">CHECKED OUT</span>`;
                
                const newRow = `
                    <tr class="hover:bg-gray-50 transition duration-150">
                        <td class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">${id}</td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-700">${record.name}</td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-green-600 font-semibold">${record.timeIn}</td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-red-600 font-semibold">${record.timeOut}</td>
                        <td class="px-6 py-4 whitespace-nowrap">${statusBadge}</td>
                    </tr>
                `;
                tbody.innerHTML += newRow;

                if (record.status === 'In') {
                    inCount++;
                } else if (record.status === 'Out') {
                    outCount++;
                }
            });
            
            // Update counts
            document.getElementById('inCount').textContent = inCount;
            document.getElementById('outCount').textContent = outCount;
        }

        /**
         * Simulates a QR code scan and updates the attendance record.
         */
        function scanQrCode(code) {
            const youthId = code.toUpperCase().trim();
            const scanFeedback = document.getElementById('scanFeedback');
            const manualCodeInput = document.getElementById('manualCodeInput');
            const now = new Date();
            const timeStr = now.toLocaleTimeString('en-US', { hour: '2-digit', minute: '2-digit', hour12: true });

            if (!youthId || !youthId.startsWith('SK-YOUTH-')) {
                scanFeedback.innerHTML = `<p class="font-semibold">Invalid Youth ID format.</p>`;
                scanFeedback.className = 'mt-4 p-4 font-medium rounded-lg transition duration-300 ease-in-out border border-red-400 bg-red-50 text-red-700';
                scanFeedback.classList.remove('hidden');
                manualCodeInput.value = '';
                setTimeout(() => { scanFeedback.classList.add('hidden'); }, 3000);
                return;
            }

            let feedbackMessage = '';
            let feedbackClass = 'border-green-400 bg-green-50 text-green-700';

            const recordExists = attendanceRecords[youthId];
            
            if (recordExists) {
                if (!recordExists.timeOut || recordExists.timeOut === 'N/A') {
                    // Time Out
                    recordExists.timeOut = timeStr;
                    recordExists.status = 'Out';
                    feedbackMessage = `${recordExists.name} successfully **CHECKED OUT** at ${timeStr}. Thank you!`;
                    feedbackClass = 'border-blue-400 bg-blue-50 text-blue-700'; // Blue for check-out
                } else {
                    // Already Timed Out
                    feedbackMessage = `${recordExists.name} is already **CHECKED OUT** at ${recordExists.timeOut}.`;
                    feedbackClass = 'border-yellow-400 bg-yellow-50 text-yellow-700';
                }
            } else {
                // New user scanning (Time In)
                const registeredName = dummyUserList[youthId];

                if (registeredName) {
                    // Successful Time In
                    attendanceRecords[youthId] = {
                        name: registeredName,
                        timeIn: timeStr,
                        timeOut: 'N/A',
                        status: 'In'
                    };
                    feedbackMessage = `${registeredName} successfully **CHECKED IN** at ${timeStr}. Welcome!`;
                    feedbackClass = 'border-green-400 bg-green-50 text-green-700';
                } else {
                    // User not found (not registered for the event/in the system)
                    feedbackMessage = `Youth ID **${youthId}** not found. Please verify registration.`;
                    feedbackClass = 'border-red-400 bg-red-50 text-red-700';
                }
            }

            // Display feedback
            scanFeedback.className = `mt-4 p-4 font-medium rounded-lg transition duration-300 ease-in-out border ${feedbackClass}`;
            scanFeedback.innerHTML = `<p class="font-semibold">${feedbackMessage.replace(/\*\*(.*?)\*\*/g, '<strong>$1</strong>')}</p>`;
            scanFeedback.classList.remove('hidden');

            renderAttendanceLog();
            manualCodeInput.value = '';
            
            // Hide feedback after 3 seconds
            setTimeout(() => {
                scanFeedback.classList.add('hidden');
            }, 3000);
        }
        
        /**
         * Generates and downloads the current attendance log as a CSV file.
         */
        function exportAttendanceData() {
            const records = attendanceRecords;
            if (Object.keys(records).length === 0) {
                alert("No attendance records to export for this event.");
                return;
            }

            const selectElement = document.getElementById('event-select');
            const eventTitle = selectElement.options[selectElement.selectedIndex].text.split(' (')[0];
            
            let csv = "Youth ID,Name,Time In,Time Out,Status\n";

            for (const id in records) {
                const record = records[id];
                // Enclose name in quotes to handle commas/special characters
                csv += `${id},"${record.name}",${record.timeIn},${record.timeOut},${record.status}\n`;
            }

            const filename = `Attendance_Log_${eventTitle.replace(/[^a-z0-9]/gi, '_')}_${new Date().toISOString().slice(0, 10)}.csv`;
            
            // Create a blob and trigger download
            const blob = new Blob([csv], { type: 'text/csv;charset=utf-8;' });
            const link = document.createElement("a");
            
            // Standard way to trigger a file download
            const url = URL.createObjectURL(blob);
            link.setAttribute("href", url);
            link.setAttribute("download", filename);
            link.style.visibility = 'hidden';
            document.body.appendChild(link);
            link.click();
            document.body.removeChild(link);
        }


        // Initial render on page load
        document.addEventListener('DOMContentLoaded', renderAttendanceLog);
    </script>
</body>
</html>