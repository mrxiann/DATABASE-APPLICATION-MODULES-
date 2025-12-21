<?php
session_start();

// Check if user is logged in and is a youth
if (!isset($_SESSION['user_id']) || $_SESSION['role'] !== 'youth') {
    header("Location: login.php");
    exit();
}

require_once('youth_sidebar.php');

// PHP Data Stubs (Replace with database fetch logic)
$user_name = $_SESSION['user_name'] ?? "Juan D. Dela Cruz";
$user_info = "Purok 5 Youth Resident, registered since 2025";
$events_attended = 6;
$volunteer_hours = 24;
$applications_sent = 2;
$latest_award = "SK Youth of the Month";

// Latest Activity Feed (Example Data)
$activities = [
    ['icon' => 'fas fa-check-circle', 'text' => 'Successfully registered for the <strong>Tree Planting Drive</strong>.', 'date' => 'Feb 15, 2026'],
    ['icon' => 'fas fa-briefcase', 'text' => 'Applied for the <strong>SK Admin Assistant</strong> position.', 'date' => 'Feb 10, 2026'],
];

$opportunity_title = "SK Admin Assistant (Part-Time)";
$opportunity_details = "Purok 5 - Admin/Clerical Work";
$opportunity_compensation = "P50/hr";
$opportunity_deadline = "Feb 28, 2026";
$qr_id = "SK-PH-" . str_pad($_SESSION['user_id'], 7, '0', STR_PAD_LEFT); // For the modal
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Youth Dashboard | SK Portal</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body class="bg-gray-50">
    <div class="flex h-screen">
        <?php render_youth_sidebar('dashboard'); ?>

        <main class="ml-64 flex-1 p-8 overflow-y-auto">
            
            <div class="flex justify-between items-center bg-white p-6 rounded-xl shadow-lg mb-6 border-l-4 border-indigo-500">
                <div>
                    <h2 class="text-3xl font-bold text-gray-800">Welcome Back, <?php echo htmlspecialchars($user_name); ?>!</h2>
                    <p class="text-lg text-gray-600 mt-1"><?php echo htmlspecialchars($user_info); ?></p>
                </div>
                <button onclick="document.getElementById('qrModal').classList.remove('hidden')" class="py-3 px-6 bg-indigo-600 text-white font-medium rounded-lg hover:bg-indigo-700 transition shadow-md">
                    <i class="fas fa-qrcode mr-2"></i> View My Digital ID (QR)
                </button>
            </div>
            
            <h3 class="text-2xl font-bold text-gray-800 mb-4">Your Engagement Snapshot</h3>
            <div class="grid grid-cols-1 md:grid-cols-4 gap-6 mb-8">
                <div class="bg-white p-6 rounded-xl shadow border-b-4 border-blue-500 text-center">
                    <i class="fas fa-calendar-check text-4xl text-blue-600"></i>
                    <p class="text-3xl font-bold mt-2"><?php echo $events_attended; ?></p>
                    <p class="text-sm text-gray-500">Events Attended</p>
                </div>
                <div class="bg-white p-6 rounded-xl shadow border-b-4 border-green-500 text-center">
                    <i class="fas fa-clock text-4xl text-green-600"></i>
                    <p class="text-3xl font-bold mt-2"><?php echo $volunteer_hours; ?></p>
                    <p class="text-sm text-gray-500">Volunteer Hours</p>
                </div>
                <div class="bg-white p-6 rounded-xl shadow border-b-4 border-yellow-500 text-center">
                    <i class="fas fa-briefcase text-4xl text-yellow-600"></i>
                    <p class="text-3xl font-bold mt-2"><?php echo $applications_sent; ?></p>
                    <p class="text-sm text-gray-500">Applications Sent</p>
                </div>
                <div class="bg-white p-6 rounded-xl shadow border-b-4 border-red-500 text-center">
                    <i class="fas fa-trophy text-4xl text-red-600"></i>
                    <p class="text-2xl font-bold mt-2"><?php echo htmlspecialchars($latest_award); ?></p>
                    <p class="text-sm text-gray-500">Latest Recognition</p>
                </div>
            </div>

            <div class="grid grid-cols-1 lg:grid-cols-3 gap-6">
                
                <div class="lg:col-span-1">
                    <div class="bg-white p-6 rounded-xl shadow mb-6">
                        <h3 class="text-xl font-semibold text-gray-800 mb-4"><i class="fas fa-bolt mr-2 text-yellow-500"></i> Quick Links</h3>
                        <div class="space-y-3">
                            <a href="youth_events.php" class="block p-3 bg-blue-50 hover:bg-blue-100 rounded-lg text-blue-700 font-medium transition">
                                <i class="fas fa-calendar-alt mr-2"></i> Register for New Events
                            </a>
                            <a href="youth_profile.php" class="block p-3 bg-green-50 hover:bg-green-100 rounded-lg text-green-700 font-medium transition">
                                <i class="fas fa-user-circle mr-2"></i> Update My Profile
                            </a>
                            <a href="youth_feedback.php" class="block p-3 bg-red-50 hover:bg-red-100 rounded-lg text-red-700 font-medium transition">
                                <i class="fas fa-comment-dots mr-2"></i> Submit Feedback/Inquiry
                            </a>
                        </div>
                    </div>

                    <div class="bg-white p-6 rounded-xl shadow">
                        <h3 class="text-xl font-semibold text-gray-800 mb-4 flex items-center">
                            <i class="fas fa-briefcase mr-2 text-yellow-500"></i> New Opportunities 
                        </h3>
                        <div class="space-y-4">
                            <div class="bg-white p-4 rounded-xl shadow hover:shadow-lg transition border-l-4 border-blue-500">
                                <h4 class="text-lg font-bold text-gray-900"><?php echo htmlspecialchars($opportunity_title); ?></h4>
                                <p class="text-sm text-gray-600"><?php echo htmlspecialchars($opportunity_details); ?></p>
                                <span class="text-xs font-medium text-blue-600">Compensation: <?php echo htmlspecialchars($opportunity_compensation); ?></span>
                                <span class="text-xs font-medium text-red-600 ml-3">Deadline: <?php echo htmlspecialchars($opportunity_deadline); ?></span>
                            </div>
                            <a href="youth_opportunities.php" class="text-sm font-medium text-indigo-600 hover:text-indigo-800 block text-right mt-3">
                                View All Opportunities <i class="fas fa-arrow-right ml-1"></i>
                            </a>
                        </div>
                    </div>
                </div>
                
                <div class="lg:col-span-2 bg-white p-6 rounded-xl shadow">
                    <h3 class="text-2xl font-bold text-gray-800 mb-4 border-b pb-2">My Latest Activity</h3>
                    <ul class="space-y-5">
                        <?php foreach ($activities as $activity): ?>
                            <li class="flex items-start">
                                <i class="<?php echo $activity['icon']; ?> text-lg text-indigo-500 flex-shrink-0 mt-1"></i>
                                <div class="ml-4 flex-1 border-l-2 border-gray-200 pl-4">
                                    <p class="text-base text-gray-800"><?php echo $activity['text']; ?></p> 
                                    <p class="text-xs text-gray-500 mt-0.5"><?php echo htmlspecialchars($activity['date']); ?></p>
                                </div>
                            </li>
                        <?php endforeach; ?>
                    </ul>
                </div>
            </div>

        </main>
    </div>
    
    <div id="qrModal" class="fixed z-50 inset-0 overflow-y-auto hidden" aria-labelledby="modal-title" role="dialog" aria-modal="true">
        <div class="flex items-center justify-center min-h-screen pt-4 px-4 pb-20 text-center sm:block sm:p-0">
            <div class="fixed inset-0 bg-black bg-opacity-80 transition-opacity" aria-hidden="true"></div>
            <span class="hidden sm:inline-block sm:align-middle sm:h-screen" aria-hidden="true">&#8203;</span>
            <div class="inline-block align-bottom bg-white rounded-lg text-left overflow-hidden shadow-xl transform transition-all sm:my-8 sm:align-middle sm:max-w-md sm:w-full">
                <div class="bg-white px-4 pt-5 pb-4 sm:p-6 sm:pb-4 text-center">
                    <h3 class="text-2xl leading-6 font-bold text-gray-900 mb-4">
                        Your Official SK Youth QR ID
                    </h3>
                    <div class="w-full h-auto flex justify-center py-6">
                        <img src="https://api.qrserver.com/v1/create-qr-code/?size=250x250&data=<?php echo urlencode($qr_id); ?>" alt="QR Code" class="w-48 h-48 border-4 border-indigo-600 p-2 rounded-lg">
                    </div>
                    <p class="mt-4 text-3xl font-extrabold text-gray-800"><?php echo htmlspecialchars($qr_id); ?></p>
                    <p class="text-sm text-gray-500">Present this code for event check-in/out and identification.</p>
                </div>
                <div class="bg-gray-50 px-4 py-3 sm:px-6">
                    <button type="button" onclick="document.getElementById('qrModal').classList.add('hidden')" class="w-full inline-flex justify-center rounded-md border border-transparent shadow-sm px-4 py-2 bg-indigo-600 text-base font-medium text-white hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500 sm:text-sm">
                        Close
                    </button>
                </div>
            </div>
        </div>
    </div>
</body>
</html>