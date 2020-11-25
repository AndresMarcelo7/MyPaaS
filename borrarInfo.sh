docker-compose stop
sudo rm -rf Apps
rm docker-compose.yml
cat ../docker-compose.yml > docker-compose.yml
docker system prune -af
