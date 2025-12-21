<?php
// youth_qr.php

require_once('youth_sidebar.php');

// PHP Data Stubs (Replace with secure, unique ID from database)
$youth_id = "SK-YOUTH-000452-ABC";
// Use a QR generation service URL as a placeholder
$qr_code_data_url = "https://api.qrserver.com/v1/create-qr-code/?size=300x300&data=" . urlencode($youth_id); 
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My QR Code | SK Youth Portal</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <script src="loader.js" defer></script>
</head>
<body class="bg-gray-50">
    <aside>
        <?php render_youth_sidebar('qrcode'); ?>
    </aside>
    
    <main class="ml-64 flex-1 p-8 overflow-y-auto">
        <h2 class="text-3xl font-bold text-gray-800 mb-6 border-b pb-2">My Unique Attendance QR Code</h2>
        <p class="text-gray-600 mb-8">Present this code to an SK Official at any event for quick attendance recording. You can download and save it on your phone.</p>

        <div class="max-w-xl mx-auto bg-white p-8 rounded-xl shadow-2xl">
            <div id="qrCodeContainer">
                <div class="text-center p-8 border-4 border-indigo-600 rounded-lg bg-white shadow-xl">
                    <img id="qrCodeImage" class="w-full h-auto mx-auto mb-4" 
                         src="<?php echo htmlspecialchars($qr_code_data_url); ?>" 
                         alt="Your unique attendance QR code">
                    
                    <p class="text-xl font-extrabold text-gray-800 font-mono">ID: <?php echo htmlspecialchars($youth_id); ?></p>
                    <p class="text-sm text-gray-500 mt-1">This code verifies your identity as a registered youth.</p>
                </div>
            </div>
            
            <div class="mt-8 flex justify-center space-x-4">
                <button onclick="downloadQRCode()" class="py-2.5 px-6 bg-green-600 text-white font-medium rounded-lg hover:bg-green-700 transition shadow-md flex items-center">
                    <i class="fas fa-download mr-2"></i> Download Image
                </button>
                <button onclick="window.location.reload();" class="py-2.5 px-6 bg-gray-600 text-white font-medium rounded-lg hover:bg-gray-700 transition shadow-md flex items-center">
                    <i class="fas fa-sync-alt mr-2"></i> Refresh Code
                </button>
            </div>
            
            <p class="text-center text-red-500 mt-6 font-medium">
                <i class="fas fa-exclamation-triangle mr-1"></i> Keep this code private. Do not share screenshots with others.
            </p>
        </div>
    </main>
    
    <script>
        function downloadQRCode() {
            const image = document.getElementById('qrCodeImage');
            const uniqueId = "<?php echo $youth_id; ?>"; 
            
            const imageURL = image.src; 
            const downloadLink = document.createElement('a');
            downloadLink.href = imageURL;
            downloadLink.download = `SK-ID-${uniqueId}-QRCode.png`;

            document.body.appendChild(downloadLink);
            downloadLink.click();
            document.body.removeChild(downloadLink);
        }
    </script>
</body>
</html>