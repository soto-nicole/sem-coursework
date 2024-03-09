# Use the latest MySQL image
FROM mysql
# Copy main SQL file to docker-entrypoint-initdb.d.
# SQL file is executed on container startup.
COPY world.sql /docker-entrypoint-initdb.d
# Sets the root password
ENV MYSQL_ROOT_PASSWORD example