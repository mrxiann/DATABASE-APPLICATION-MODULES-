<?php
// youth_opportunities.php

require_once('youth_sidebar.php');

// PHP Data Stubs for Opportunity Cards (Replace with database fetch logic)
$opportunities = [
    [
        'title' => 'SK Admin Assistant (Part-Time)', 
        'deadline' => 'Feb 28, 2026', 
        'type' => 'Part-Time Job', 
        'description' => 'Support the SK council with clerical work, organizing documents, and logistics for minor events. Requires basic computer skills.',
        'location' => 'SK Office',
        'commitment' => 'Until end of current term',
        'type_class' => 'text-red-800 bg-red-100',
        'status' => 'open' // open, closed, applied
    ],
    [
        'title' => 'Barangay Health Volunteer', 
        'deadline' => 'Mar 15, 2026', 
        'type' => 'Volunteer', 
        'description' => 'Assist the Barangay Health Center with outreach programs, vaccination drives, and record-keeping. Great for Pre-Med students.',
        'location' => 'Barangay Health Center',
        'commitment' => 'Flexible, 2 days/week',
        'type_class' => 'text-green-800 bg-green-100',
        'status' => 'applied'
    ],
    [
        'title' => 'SK Communications Intern', 
        'deadline' => 'Dec 15, 2025', 
        'type' => 'Internship/OJT', 
        'description' => 'Assist the SK Council with social media management, content creation, and documenting events. Excellent for MassComm or Marketing students.',
        'location' => 'SK Office',
        'commitment' => '3-month commitment',
        'type_class' => 'text-orange-800 bg-orange-100',
        'status' => 'closed'
    ],
];
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Opportunities | SK Youth Portal</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <script src="loader.js" defer></script>
</head>
<body class="bg-gray-50">
    <aside>
        <?php render_youth_sidebar('opportunities'); ?>
    </aside>
    
    <main class="ml-64 flex-1 p-8 overflow-y-auto">
        <h2 class="text-3xl font-bold text-gray-800 mb-6 border-b pb-2">Job & Volunteer Opportunities</h2>
        <p class="text-gray-600 mb-8">Find ways to gain experience and earn extra income in our community.</p>

        <div class="bg-white p-4 rounded-xl shadow-md mb-8 flex space-x-4 items-center">
             <input type="text" placeholder="Search title or keyword..." class="py-2 px-4 border rounded-lg flex-1 max-w-sm">
             <select class="py-2 px-4 border rounded-lg text-sm">
                <option value="">Filter by Type</option>
                <option value="job">Part-Time Job</option>
                <option value="volunteer">Volunteer</option>
                <option value="internship">Internship/OJT</option>
             </select>
             <select class="py-2 px-4 border rounded-lg text-sm">
                <option value="">Filter by Status</option>
                <option value="open">Open for Application</option>
                <option value="closing">Closing Soon</option>
             </select>
             <button class="py-2 px-4 bg-indigo-500 text-white font-medium rounded-lg hover:bg-indigo-600 transition text-sm">Apply Filters</button>
        </div>

        <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
            
            <?php foreach ($opportunities as $opp): ?>
                <div class="bg-white p-6 rounded-xl shadow-lg border-l-4 border-indigo-500 hover:shadow-xl transition duration-300">
                    <div class="flex justify-between items-start">
                        <div>
                            <span class="px-3 py-1 text-xs font-semibold <?php echo htmlspecialchars($opp['type_class']); ?> rounded-full"><?php echo htmlspecialchars($opp['type']); ?></span>
                            <h3 class="text-xl font-bold text-gray-900 mt-2"><?php echo htmlspecialchars($opp['title']); ?></h3>
                        </div>
                        <span class="text-sm text-gray-500 bg-gray-100 p-2 rounded-lg">Deadline: **<?php echo htmlspecialchars($opp['deadline']); ?>**</span>
                    </div>
                    <p class="text-gray-600 mt-3 mb-4"><?php echo htmlspecialchars($opp['description']); ?></p>
                    <div class="flex justify-between items-center border-t pt-4">
                        <span class="text-sm font-medium text-gray-700">
                            <i class="fas fa-map-marker-alt mr-1"></i> <?php echo htmlspecialchars($opp['location']); ?> 
                            <i class="fas fa-calendar-week ml-3 mr-1"></i> <?php echo htmlspecialchars($opp['commitment']); ?>
                        </span>
                        
                        <?php if ($opp['status'] === 'open'): ?>
                            <button class="px-4 py-2 bg-indigo-600 text-white text-sm font-medium rounded-lg hover:bg-indigo-700 transition">
                                View & Apply
                            </button>
                        <?php elseif ($opp['status'] === 'applied'): ?>
                            <button class="px-4 py-2 bg-green-600 text-white text-sm font-medium rounded-lg cursor-default">
                                Applied <i class="fas fa-check ml-1"></i>
                            </button>
                        <?php else: ?>
                            <button disabled class="px-4 py-2 bg-gray-400 text-white text-sm font-medium rounded-lg cursor-not-allowed">
                                Closed
                            </button>
                        <?php endif; ?>
                    </div>
                </div>
            <?php endforeach; ?>

        </div>
    </main>
</body>
</html>