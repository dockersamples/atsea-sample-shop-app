
$wd = $pwd

## SQL Server Database

# build the database builder
cd $wd\docker\database
docker build -t atsea-db-builder -f Dockerfile.builder .

# build the dacpac for the database schema
rmdir -Force -Recurse out
mkdir out
docker run --rm -v $pwd\out:c:\bin -v $wd\dotnet:c:\src atsea-db-builder c:\src\Docker.AtSea\Docker.AtSea.Database\build.ps1

# build the database image
docker build -t atsea-db .


# build the database builder
cd $wd\docker\database
docker build -t atsea-db-builder -f Dockerfile.builder .

# build the dacpac for the database schema
rmdir -Force -Recurse out
mkdir out
docker run --rm -v $pwd\out:c:\bin -v $wd\dotnet:c:\src atsea-db-builder c:\src\Docker.AtSea\Docker.AtSea.Database\build.ps1

# build the database image
docker build -t atsea-db .


## .NET WebApi
cd $wd\docker\api
docker build -t atsea-api-builder -f Dockerfile.builder .

# build the web output
rmdir -Force -Recurse out
mkdir out
docker run --rm -v $pwd\out:c:\bin -v $wd\dotnet:c:\src atsea-api-builder c:\src\Docker.AtSea\Docker.AtSea.Api\build.ps1

# build the web api image
docker build -t atsea-api .

cd $wd