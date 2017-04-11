# escape=`
FROM microsoft/mssql-server-windows-express
SHELL ["powershell", "-Command", "$ErrorActionPreference = 'Stop';"]

EXPOSE 1433
VOLUME c:\database
ENV sa_password g0rd0n1!

WORKDIR c:\init
COPY .\bin\Debug .
COPY InitializeDatabase.ps1 .

CMD ./InitializeDatabase.ps1 -sa_password $env:sa_password -Verbose