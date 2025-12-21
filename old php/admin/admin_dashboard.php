<?php
session_start();

// Check if user is logged in and is an admin
if (!isset($_SESSION['user_id']) || $_SESSION['role'] !== 'admin') {
    header("Location: login.php");
    exit();
}

require_once('admin_sidebar.php');

// PHP Data Stubs (Replace with actual database counts)
$total_youth_users = 452;
$verified_youth = 437;
$ongoing_events = 3;
$open_opportunities = 5;
$total_attendance_records = 1500;
$pending_users = 15;
$pending_feedback = 7;
$new_applications = 2;

$recent_events = [
    ['title' => 'Coastal Clean-Up Drive', 'attendees' => 85, 'date' => 'Feb 15, 2026', 'status_color' => 'bg-green-100', 'text_color' => 'text-green-800'],
    ['title' => 'SK Admin Assistant Hiring', 'applicants' => 12, 'date' => 'Feb 10, 2026', 'status_color' => 'bg-blue-100', 'text_color' => 'text-blue-800'],
    ['title' => 'Youth Leadership Summit', 'attendees' => 120, 'date' => 'Jan 28, 2026', 'status_color' => 'bg-gray-100', 'text_color' => 'text-gray-800'],
];
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard | SK Admin Portal</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body class="bg-gray-50">
    <div class="flex h-screen">
        
        <?php render_admin_sidebar('dashboard'); ?>

        <main class="ml-64 flex-1 p-8 overflow-y-auto">
            <h2 class="text-3xl font-bold text-gray-800 mb-6 border-b pb-2">Admin Dashboard Overview</h2>
            
            <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
                <div class="bg-white p-6 rounded-xl shadow border-l-4 border-blue-500">
                    <div class="flex items-center justify-between">
                        <p class="text-lg font-medium text-gray-500">Total Youth Users</p>
                        <i class="fas fa-users text-2xl text-blue-500"></i>
                    </div>
                    <p class="text-4xl font-bold text-gray-900 mt-2"><?php echo $total_youth_users; ?></p>
                    <p class="text-xs text-gray-500 mt-1"><?php echo $verified_youth; ?> Verified</p>
                </div>
                <div class="bg-white p-6 rounded-xl shadow border-l-4 border-green-500">
                    <div class="flex items-center justify-between">
                        <p class="text-lg font-medium text-gray-500">Ongoing Events</p>
                        <i class="fas fa-calendar-check text-2xl text-green-500"></i>
                    </div>
                    <p class="text-4xl font-bold text-gray-900 mt-2"><?php echo $ongoing_events; ?></p>
                    <p class="text-xs text-gray-500 mt-1">Check Event Management</p>
                </div>
                <div class="bg-white p-6 rounded-xl shadow border-l-4 border-yellow-500">
                    <div class="flex items-center justify-between">
                        <p class="text-lg font-medium text-gray-500">Open Opportunities</p>
                        <i class="fas fa-briefcase text-2xl text-yellow-500"></i>
                    </div>
                    <p class="text-4xl font-bold text-gray-900 mt-2"><?php echo $open_opportunities; ?></p>
                    <p class="text-xs text-gray-500 mt-1">Jobs/Volunteer Slots</p>
                </div>
                <div class="bg-white p-6 rounded-xl shadow border-l-4 border-indigo-500">
                    <div class="flex items-center justify-between">
                        <p class="text-lg font-medium text-gray-500">Total Attendance</p>
                        <i class="fas fa-clipboard-list text-2xl text-indigo-500"></i>
                    </div>
                    <p class="text-4xl font-bold text-gray-900 mt-2"><?php echo $total_attendance_records; ?></p>
                    <p class="text-xs text-gray-500 mt-1">Total check-in/out records</p>
                </div>
            </div>

            <div class="grid grid-cols-1 lg:grid-cols-3 gap-6">
                <div class="lg:col-span-2 bg-white p-6 rounded-xl shadow-lg">
                    <h3 class="text-xl font-semibold text-gray-800 mb-4 border-b pb-2"><i class="fas fa-chart-line mr-2 text-indigo-500"></i> Recent Activity Log</h3>
                    <ul class="space-y-4">
                        <?php foreach ($recent_events as $event): ?>
                            <li class="p-4 rounded-lg flex justify-between items-center <?php echo $event['status_color']; ?> border border-gray-200">
                                <div>
                                    <p class="font-medium text-gray-900"><?php echo htmlspecialchars($event['title']); ?></p>
                                    <p class="text-sm text-gray-600">
                                        <?php if (isset($event['attendees'])): ?>
                                            <strong><?php echo $event['attendees']; ?></strong> Attendees checked in.
                                        <?php elseif (isset($event['applicants'])): ?>
                                            <strong><?php echo $event['applicants']; ?></strong> New applications received.
                                        <?php else: ?>
                                            Viewed by: Admin Officer.
                                        <?php endif; ?>
                                    </p>
                                </div>
                                <span class="text-xs font-semibold <?php echo $event['text_color']; ?>"><?php echo htmlspecialchars($event['date']); ?></span>
                            </li>
                        <?php endforeach; ?>
                    </ul>
                    <div class="mt-4 text-right">
                        <a href="admin_event_management.php" class="text-sm font-medium text-indigo-600 hover:text-indigo-800">View All Events <i class="fas fa-arrow-right ml-1"></i></a>
                    </div>
                </div>

                <div class="lg:col-span-1 bg-white p-6 rounded-xl shadow-lg">
                    <h3 class="text-xl font-semibold text-gray-800 mb-4 flex items-center">
                        <i class="fas fa-list-ol mr-2 text-blue-500"></i> Pending Admin Actions
                    </h3>
                    <ul class="space-y-3">
                        <li class="p-3 border-l-4 border-red-500 bg-red-50 rounded-lg flex justify-between items-center">
                            <span class="font-medium"><?php echo $pending_users; ?> new youth users pending verification.</span>
                            <a href="admin_user_management.php" class="text-sm text-red-600 hover:underline">Review</a>
                        </li>
                        <li class="p-3 border-l-4 border-yellow-500 bg-yellow-50 rounded-lg flex justify-between items-center">
                            <span class="font-medium"><?php echo $pending_feedback; ?> pieces of youth feedback require a reply.</span>
                            <a href="admin_feedback_management.php" class="text-sm text-yellow-600 hover:underline">Manage</a>
                        </li>
                        <li class="p-3 border-l-4 border-green-500 bg-green-50 rounded-lg flex justify-between items-center">
                            <span class="font-medium"><?php echo $new_applications; ?> new opportunity applications received.</span>
                            <a href="admin_opportunities.php" class="text-sm text-green-600 hover:underline">Check</a>
                        </li>
                    </ul>
                </div>
            </div>
        </main>
    </div>
</body>
</html>