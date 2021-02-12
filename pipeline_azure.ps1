curl  https://downloads.veracode.com/securityscan/pipeline-scan-LATEST.zip -o pipeline-scan.zip
Expand-Archive -Force -Path pipeline-scan.zip -DestinationPath veracode_scanner
java -jar veracode_scanner\\pipeline-scan.jar --veracode_api_id '${VERACODE_API_ID}' --veracode_api_key '${VERACODE_API_KEY}' --file target/verademo.war