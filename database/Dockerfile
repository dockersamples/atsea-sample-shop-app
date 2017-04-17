FROM postgres

# Copy the database initialize script: 
# Contents of /docker-entrypoint-initdb.d are run on startup
RUN mkdir -p /images/

COPY pg_hba.conf /usr/share/postgresql/9.6/
COPY postgresql.conf /usr/share/postgresql/9.6/
ADD  docker-entrypoint-initdb.d/ /docker-entrypoint-initdb.d/

# Default values for passwords and database name. Can be overridden on docker run
ENV POSTGRES_USER gordonuser
ENV POSTGRES_DB atsea
