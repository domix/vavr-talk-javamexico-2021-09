
docker run --rm \
  -e POSTGRES_PASSWORD=secret \
  -e POSTGRES_USER=user \
  -e POSTGRES_DB=db \
  -p 5423:5432 \
  -d \
  postgres:13.4-alpine3.14
