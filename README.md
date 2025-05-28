1. Build the project
2. Create the docker image with "docker build -t trashclassifier ."
3. Run the image with "docker run -p 8080:8080 trashclassifier"
4. Start ngrok
5. Use your static urls to readdress on 8080 with "ngrok http --url="YOUR_URL" 8080
6. Build the android application with your ngrok static url
