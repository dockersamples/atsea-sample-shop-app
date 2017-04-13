
# At Sea Art Shop - for Windows

This Windows port of the art shop uses SQL Server for the database and ASP.NET WebApi for the REST API.

It uses Windows containers, so you need to have [Docker for Windows](https://store.docker.com/editions/community/docker-ce-desktop-windows?tab=description) on Windows 10, or [Docker on Windows Server 2016](https://store.docker.com/editions/enterprise/docker-ee-server-windows?tab=description) to build and run the images.


## Build

Use the PowerShell [build.ps1](build.ps1) script to build the containers:

```
.\build.ps1
```

That creates the following images:

- `atsea-db-builder` - build agent for the database
- `atsea-db` - SQL Server database with schema deployed
- `atsea-api-builder` - build agent for the WebApi
- `atsea-api` - .NET WebApi for the shop back-end

> If you don't already have the Windows and SQL Server base images, it will take a while to download them.

## Run

Use Docker Compose to start the solution locally:

```
docker-compose up -d
```

The API will be listening on port `8080`, but if you want to access it from your local machine you need to use the IP address of the API container.
