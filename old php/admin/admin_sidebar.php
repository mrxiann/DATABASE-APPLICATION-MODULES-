<?php
// admin_sidebar.php

/**
 * Renders the admin sidebar component, highlighting the current page.
 * @param string $current_page_data_page The data-page attribute value of the current page (e.g., 'dashboard', 'users').
 */
function render_admin_sidebar($current_page_data_page) {
    // Map data-page attributes to file names and icon/text
    $nav_items = [
        'dashboard' => ['href' => 'admin_dashboard.php', 'icon' => 'fas fa-tachometer-alt', 'text' => 'Dashboard'],
        'users' => ['href' => 'admin_user_management.php', 'icon' => 'fas fa-users-cog', 'text' => 'User Management'],
        'events' => ['href' => 'admin_event_management.php', 'icon' => 'fas fa-calendar-check', 'text' => 'Event Management'],
        'opportunities' => ['href' => 'admin_opportunities.php', 'icon' => 'fas fa-briefcase', 'text' => 'Opportunities Mgmt'],
        'attendance' => ['href' => 'admin_attendance_record.php', 'icon' => 'fas fa-clipboard-list', 'text' => 'Attendance Record'],
        'feedback' => ['href' => 'admin_feedback_management.php', 'icon' => 'fas fa-reply', 'text' => 'Manage Feedback'],
    ];
    
    // Get admin info from session
    $admin_name = $_SESSION['user_name'] ?? "SK Admin Officer";
    $admin_role = "SK Secretary, Zone 1";
    $admin_initials = substr($admin_name, 0, 2);

    echo '<div class="flex flex-col w-64 bg-blue-900 text-white shadow-xl h-full fixed left-0 top-0">';
    echo '    <div class="flex items-center justify-center h-16 border-b border-blue-800">';
    echo '        <span class="text-2xl font-bold tracking-wider">SK Admin Portal</span>';
    echo '    </div>';
    echo '    <nav class="flex-1 p-4 space-y-2 overflow-y-auto">';
    
    foreach ($nav_items as $data_page => $item) {
        $isActive = ($current_page_data_page === $data_page) ? 'bg-blue-800 text-white font-medium' : 'hover:bg-blue-700';

        echo '        <a href="' . $item['href'] . '" class="nav-item flex items-center p-3 rounded-lg ' . $isActive . ' transition duration-150" data-page="' . $data_page . '">';
        echo '            <i class="' . $item['icon'] . ' mr-3"></i> ' . $item['text'];
        echo '        </a>';
    }

    echo '    </nav>';
    
    // Admin Info Section
    echo '    <div class="p-4 border-t border-blue-800">';
    echo '        <div class="flex items-center">';
    echo '            <img class="h-10 w-10 rounded-full object-cover border-2 border-white" src="https://ui-avatars.com/api/?name=' . urlencode($admin_initials) . '&background=fff&color=121C42&bold=true" alt="Admin Avatar">';
    echo '            <div class="ml-3 leading-tight">';
    echo '                <p class="text-sm font-semibold text-white">' . htmlspecialchars($admin_name) . '</p>';
    echo '                <p class="text-xs text-blue-200">' . htmlspecialchars($admin_role) . '</p>';
    echo '            </div>';
    echo '            <a href="logout.php" class="ml-auto text-blue-200 hover:text-white transition duration-150" title="Logout">';
    echo '                <i class="fas fa-sign-out-alt text-xl"></i>';
    echo '            </a>';
    echo '        </div>';
    echo '    </div>';
    
    echo '</div>';
}
?>