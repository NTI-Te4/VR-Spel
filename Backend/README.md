# VR Game Server Backend

## Features

- Add scores with automatic duplicate username handling (appends numbers like 1, 2, 3 to usernames)
- Retrieve scores with timestamps
- Get ranked scores for leaderboards
- Get scores for specific users with or without rank information
- Comprehensive logging for all operations
- Proper error handling with meaningful error messages

## API Endpoints

### Add Score
- **POST** `/api/score/addscore`
- Body: `{"username": "string", "score": number}`

### Get All Scores
- **GET** `/api/score/getScore`

### Get Scores for Specific User (without rank)
- **GET** `/api/score/getScore/{username}`

### Get Ranked Scores for Specific User
- **GET** `/api/score/getRankedScore/{username}`

### Get All Ranked Scores
- **GET** `/api/score/getRankedScores`

## Docker Deployment

### Build the Docker Image
```bash
docker build -t vr-game-server -f VRGameServerApp.DockerFile .
```

### Run the Docker Container
```bash
docker run -p 8001:8001 -e SPRING_DATASOURCE_URL=jdbc:mysql://host:port/database vr-game-server
```

### Push to Docker Hub
```bash
# Tag the image
docker tag vr-game-server your-dockerhub-username/vr-game-server:latest

# Push to Docker Hub
docker push your-dockerhub-username/vr-game-server:latest
```

## Environment Variables

- `SERVER_PORT` - Server port (default: 8001)
- `SPRING_DATASOURCE_URL` - Database URL (default: jdbc:mysql://localhost:3306/unityvr_db)
- `SPRING_DATASOURCE_USERNAME` - Database username (default: root)
- `SPRING_DATASOURCE_PASSWORD` - Database password (default: empty)

## Logging

All application logs are output to stdout/stderr which makes them available in Docker logs:
```bash
docker logs <container-id>
```

## Database

The application uses MySQL with JPA/Hibernate. The schema is automatically updated on startup.

## Error Handling

All endpoints provide meaningful error messages instead of generic 500 errors:
- Invalid usernames (empty/null) return 400 Bad Request
- Negative scores return 400 Bad Request 
- Server errors return 500 Internal Server Error with description