FROM mysql

# Setup database
RUN mkdir -p /images/
COPY ../images /images

# Default values for passwords and database name. Can be overridden on docker run
ENV MYSQL_USER gordonuser
ENV MYSQL_PASSWORD gordonpassword
ENV MYSQL_DB atsea
