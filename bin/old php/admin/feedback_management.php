<?php
// admin_feedback_management.php
require_once('admin_sidebar.php');

// --- 1. PHP Data Stubs (Using ID as key for easy lookup) ---
// NOTE: In a real application, this array would be fetched from a database.
// The sentiment data is kept in the data structure but the filter dropdown is removed from the UI.
$feedback_items = [
    1 => ['id' => 1, 'subject' => 'The event was fantastic!', 'sentiment' => 'Appreciation', 'youth' => 'Juan D. Cruz', 'date' => 'Feb 10, 2026', 'status' => 'resolved', 'message' => 'The Coastal Clean-Up Drive was excellently organized. The check-in was smooth, and everyone enjoyed the free snacks.'],
    2 => ['id' => 2, 'subject' => 'Website broken on mobile', 'sentiment' => 'Complaint/Issue', 'youth' => 'Maria S. Reyes', 'date' => 'Feb 05, 2026', 'status' => 'pending review', 'message' => 'When I try to register for an event on my phone, the "Register Now" button is off-screen. Please fix the mobile layout.'],
    3 => ['id' => 3, 'subject' => 'Suggestion: Add a new type of seminar', 'sentiment' => 'Suggestion', 'youth' => 'Benito M. Diaz', 'date' => 'Jan 25, 2026', 'status' => 'in progress', 'message' => 'I suggest we add a seminar about financial literacy for students. It would be very helpful.'],
    4 => ['id' => 4, 'subject' => 'Lack of communication on project X', 'sentiment' => 'Complaint/Issue', 'youth' => 'Crispin G. Diaz', 'date' => 'Mar 01, 2026', 'status' => 'pending review', 'message' => 'We need clearer updates on the Youth Development project timeline.'],
];

// --- 2. PHP Logic for Modal Submission (Simulated Persistence) ---
$message = '';
if ($_SERVER["REQUEST_METHOD"] == "POST" && ($_POST['action'] ?? '') === 'update_feedback') {
    $feedback_id = (int)($_POST['feedback_id'] ?? 0);
    $new_status = trim(strtolower($_POST['update_status'] ?? 'pending review'));
    $reply_text = htmlspecialchars($_POST['admin_reply'] ?? 'No reply sent');
    
    // Simulate updating the status of the item in the array
    if (isset($feedback_items[$feedback_id])) {
        $feedback_items[$feedback_id]['status'] = $new_status;
    }
    
    $message = '<div class="bg-green-100 border border-green-400 text-green-700 px-4 py-3 rounded relative mb-4" role="alert"><strong class="font-bold">Success!</strong><span class="block sm:inline"> Feedback #' . $feedback_id . ' status updated to "' . ucwords($new_status) . '" and simulated reply sent.</span></div>';
}

// --- 3. Prepare Data for JavaScript ---
$js_feedback_items = json_encode(array_values($feedback_items)); // Convert to indexed array for JS
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Feedback Management | SK Admin Portal</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        /* Custom class to handle the sidebar offset for larger screens */
        @media (min-width: 1024px) {
            .ml-64 {
                margin-left: 16rem;
            }
        }
        /* Style to limit the message display in the card view */
        .line-clamp-2 {
            display: -webkit-box;
            -webkit-line-clamp: 2;
            -webkit-box-orient: vertical;
            overflow: hidden;
        }
    </style>
</head>
<body class="bg-gray-100 font-sans">
    <div class="flex min-h-screen">
        
        <?php render_admin_sidebar('feedback'); ?>

        <main class="ml-64 flex-1 p-8 overflow-y-auto">
            <h2 class="text-3xl font-bold text-gray-800 mb-6 border-b pb-2"><i class="fas fa-comment-dots mr-2 text-indigo-600"></i> Feedback Management</h2>
            <p class="text-gray-600 mb-8">Review, track, and respond to all youth-submitted feedback and inquiries.</p>

            <?php echo $message; // Display PHP success message ?>

            <div class="bg-white p-6 rounded-xl shadow-2xl mb-8">
                <div class="flex flex-col md:flex-row justify-between items-start md:items-center mb-6 space-y-4 md:space-y-0">
                    <div class="w-full md:w-1/2">
                        <input type="text" id="feedbackSearch" 
                               oninput="filterFeedbackItems()"
                               placeholder="Search subject or youth name..." 
                               class="py-2 px-4 border border-gray-300 rounded-lg w-full focus:ring-indigo-500 focus:border-indigo-500">
                    </div>
                    
                    <div class="w-full md:w-1/3">
                        <select id="statusFilter" onchange="filterFeedbackItems()" class="py-2 px-4 border border-gray-300 rounded-lg text-sm w-full focus:ring-indigo-500 focus:border-indigo-500">
                            <option value="">Filter by Status (All)</option>
                            <option value="pending review">Pending Review</option>
                            <option value="in progress">In Progress</option>
                            <option value="resolved">Resolved</option>
                        </select>
                    </div>
                </div>
                
                <div id="feedbackListContainer" class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
                    </div>
            </div>
        </main>

        <div id="feedbackModal" class="fixed z-50 inset-0 overflow-y-auto hidden" aria-labelledby="modal-title" role="dialog" aria-modal="true">
            <div class="flex items-end justify-center min-h-screen pt-4 px-4 pb-20 text-center sm:block sm:p-0">
                <div class="fixed inset-0 bg-gray-900 bg-opacity-50 transition-opacity" aria-hidden="true"></div>
                <span class="hidden sm:inline-block sm:align-middle sm:h-screen" aria-hidden="true">&#8203;</span>
                <div class="inline-block align-bottom bg-white rounded-xl text-left overflow-hidden shadow-2xl transform transition-all sm:my-8 sm:align-middle sm:max-w-3xl sm:w-full">
                    <form action="<?php echo htmlspecialchars($_SERVER['PHP_SELF']); ?>" method="POST">
                        <input type="hidden" name="action" value="update_feedback">
                        <input type="hidden" name="feedback_id" id="modalFeedbackId" value="">
                        <div class="bg-white px-4 pt-5 pb-4 sm:p-6 sm:pb-4">
                            <div class="flex items-start">
                                <div class="mx-auto flex-shrink-0 flex items-center justify-center h-12 w-12 rounded-full bg-indigo-100 sm:mx-0 sm:h-10 sm:w-10">
                                    <i class="fas fa-envelope-open-text text-indigo-600"></i>
                                </div>
                                <div class="mt-0 ml-4 text-left w-full">
                                    <h3 class="text-2xl leading-6 font-bold text-gray-900" id="modalSubject"></h3>
                                    <div class="mt-3 grid grid-cols-2 gap-4 text-sm font-medium">
                                        <p class="text-gray-500">Submitted by: <strong id="modalYouthName" class="text-gray-800"></strong></p>
                                        <p class="text-gray-500">Sentiment: <strong id="modalSentiment" class="text-indigo-600"></strong></p>
                                    </div>
                                </div>
                                <button type="button" onclick="document.getElementById('feedbackModal').classList.add('hidden')" class="ml-auto text-gray-400 hover:text-gray-600">
                                    <i class="fas fa-times"></i>
                                </button>
                            </div>
                            
                            <div class="mt-6 border-t pt-4">
                                <h4 class="text-lg font-semibold mb-2 text-gray-800">Youth Message</h4>
                                <p class="p-4 bg-gray-50 border border-gray-200 rounded-lg text-gray-700 whitespace-pre-wrap" id="modalMessage"></p>
                                
                                <div class="mt-6 border-t pt-4">
                                    <h4 class="text-lg font-semibold mb-3 border-b pb-1 text-gray-800">Admin Action & Tracking</h4>
                                    
                                    <div class="mb-4">
                                        <label for="updateStatus" class="block text-sm font-medium text-gray-700">Current Status: <strong id="modalStatus" class="ml-2 text-red-600"></strong></label>
                                        <select id="updateStatus" name="update_status" class="mt-1 block w-full p-2 border border-gray-300 rounded-lg focus:ring-indigo-500 focus:border-indigo-500">
                                            <option value="pending review">Pending Review</option>
                                            <option value="in progress">In Progress</option>
                                            <option value="resolved">Resolved</option>
                                        </select>
                                    </div>
                                    
                                    <div>
                                        <label for="adminReply" class="block text-sm font-medium text-gray-700 mt-3">Admin Reply (Simulated Email Response)</label>
                                        <textarea id="adminReply" name="admin_reply" rows="4" class="mt-1 block w-full p-2 border border-gray-300 rounded-lg focus:ring-indigo-500 focus:border-indigo-500" placeholder="Type your formal response to the youth user. This action simulates sending an email and saving the status."></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="bg-gray-50 px-4 py-3 sm:flex sm:flex-row-reverse sm:px-6">
                            <button type="submit" class="w-full inline-flex justify-center rounded-md border border-transparent shadow-sm px-4 py-2 bg-indigo-600 text-base font-medium text-white hover:bg-indigo-700 sm:ml-3 sm:w-auto sm:text-sm transition duration-150">
                                <i class="fas fa-save mr-2"></i> Save Status & Send Reply
                            </button>
                            <button type="button" onclick="document.getElementById('feedbackModal').classList.add('hidden')" class="mt-3 w-full inline-flex justify-center rounded-md border border-gray-300 shadow-sm px-4 py-2 bg-white text-base font-medium text-gray-700 hover:bg-gray-100 sm:mt-0 sm:ml-3 sm:w-auto sm:text-sm transition duration-150">
                                Cancel
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script>
        // Data structure from PHP
        const allFeedbackItems = <?php echo $js_feedback_items; ?>;

        /**
         * Generates the HTML badge for a given status.
         */
        function getStatusBadgeHtml(status) {
            let colorClass = '';
            let text = status.toUpperCase();
            if (status === 'resolved') {
                colorClass = 'border-green-500 bg-green-100 text-green-800';
                text = 'RESOLVED';
            } else if (status === 'pending review') {
                colorClass = 'border-red-500 bg-red-100 text-red-800';
                text = 'PENDING';
            } else if (status === 'in progress') {
                colorClass = 'border-yellow-500 bg-yellow-100 text-yellow-800';
                text = 'IN PROGRESS';
            } else {
                colorClass = 'border-gray-500 bg-gray-100 text-gray-800';
            }
            // Using border and padding on the badge for better visibility in the card
            return `<span class="px-2 py-0.5 inline-flex text-xs leading-5 font-bold rounded-full border ${colorClass}">${text}</span>`;
        }

        /**
         * Populates the list container with the provided array of feedback items using a card design.
         */
        function renderFeedbackList(items) {
            const container = document.getElementById('feedbackListContainer');
            container.innerHTML = ''; // Clear existing content

            if (items.length === 0) {
                container.innerHTML = '<div class="md:col-span-2 lg:col-span-3 text-center py-6 text-xl text-gray-500 border-2 border-dashed border-gray-300 rounded-xl bg-gray-50"><i class="fas fa-exclamation-circle mr-2"></i> No feedback items match your criteria.</div>';
                return;
            }

            items.forEach(item => {
                const card = `
                    <div class="bg-white p-5 rounded-xl shadow-lg border-l-4 border-indigo-600 flex flex-col space-y-3 hover:shadow-2xl transition duration-300">
                        <div class="flex justify-between items-start">
                            <span class="text-xs font-semibold text-gray-400">#${item.id} | ${item.date}</span>
                            ${getStatusBadgeHtml(item.status)}
                        </div>
                        
                        <h4 class="text-xl font-extrabold text-gray-900 truncate">${item.subject}</h4>
                        <div class="text-sm font-medium text-gray-600">
                            <span class="text-indigo-600 mr-4"><i class="fas fa-tag mr-1"></i> ${item.sentiment}</span>
                        </div>
                        
                        <div class="text-base text-gray-700 flex items-center space-x-2">
                            <i class="fas fa-user-circle text-gray-500"></i>
                            <span class="font-semibold">${item.youth}</span>
                        </div>
                        
                        <p class="text-sm text-gray-600 line-clamp-2 italic border-t pt-3">
                            "${item.message}"
                        </p>
                        
                        <div class="border-t pt-3 flex justify-end">
                            <button onclick='openFeedbackModal(${JSON.stringify(item)})' 
                                    class="text-sm text-indigo-600 hover:text-indigo-800 font-semibold transition duration-150">
                                <i class="fas fa-eye mr-1"></i> View & Manage
                            </button>
                        </div>
                    </div>
                `;
                container.innerHTML += card;
            });
        }

        /**
         * Filters the global feedback items based on current search input and status dropdown values.
         */
        function filterFeedbackItems() {
            const searchTerm = document.getElementById('feedbackSearch').value.toLowerCase();
            const statusFilter = document.getElementById('statusFilter').value.toLowerCase();
            // Sentiment filter removal reflected here

            const filteredItems = allFeedbackItems.filter(item => {
                // 1. Search Filter (Youth Name or Subject)
                const searchMatch = !searchTerm || 
                                    item.subject.toLowerCase().includes(searchTerm) || 
                                    item.youth.toLowerCase().includes(searchTerm);

                // 2. Status Filter
                const statusMatch = !statusFilter || item.status.toLowerCase() === statusFilter;

                return searchMatch && statusMatch;
            });

            renderFeedbackList(filteredItems);
        }

        /**
         * Populates the modal with feedback details and opens it.
         */
        function openFeedbackModal(item) {
            document.getElementById('modalSubject').textContent = item.subject;
            document.getElementById('modalYouthName').textContent = item.youth;
            document.getElementById('modalSentiment').textContent = item.sentiment;
            // Display current status in text format
            document.getElementById('modalStatus').textContent = item.status.charAt(0).toUpperCase() + item.status.slice(1);
            document.getElementById('modalMessage').textContent = item.message;
            document.getElementById('modalFeedbackId').value = item.id;
            
            // Set the correct status in the dropdown
            document.getElementById('updateStatus').value = item.status; 
            
            // Clear reply box when opening a new one
            document.getElementById('adminReply').value = '';

            document.getElementById('feedbackModal').classList.remove('hidden');
        }

        // Initial render on page load
        document.addEventListener('DOMContentLoaded', () => {
            renderFeedbackList(allFeedbackItems); 
        });
    </script>
</body>
</html>