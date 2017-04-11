# escape=`
FROM microsoft/mssql-server-windows-express:2016-sp1-windowsservercore-10.0.14393.693
SHELL ["powershell", "-Command", "$ErrorActionPreference = 'Stop'; $ProgressPreference = 'SilentlyContinue';"]

VOLUME c:\database
ENV sa_password g0rd0n1!

WORKDIR c:\init
COPY .\out .
COPY InitializeDatabase.ps1 .

CMD ./InitializeDatabase.ps1 -sa_password $env:sa_password -Verbose

HEALTHCHECK CMD powershell -command `
    try { `
     $result = invoke-sqlcmd -Query 'SELECT TOP 1 1 FROM Products' -Database AtSeaDB; `
     if ($result[0] -eq 1) { return 0} `
     else {return 1}; `
    } catch { return 1 }