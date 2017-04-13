# escape=`
FROM microsoft/windowsservercore:10.0.14393.953
SHELL ["powershell", "-Command", "$ErrorActionPreference = 'Stop'; $ProgressPreference = 'SilentlyContinue';"]

RUN Install-PackageProvider -Name chocolatey -RequiredVersion 2.8.5.130 -Force; `
    Install-Package -Name microsoft-build-tools -RequiredVersion 14.0.25420.1 -Force; `
    Install-Package -Name netfx-4.5.2-devpack -RequiredVersion 4.5.5165101 -Force

RUN Install-Package nuget.commandline -RequiredVersion 3.4.3 -Force; `
    & C:\Chocolatey\bin\nuget install Microsoft.Data.Tools.Msbuild -Version 10.0.61026

WORKDIR C:\Program Files (x86)\MSBuild\14.0\Bin
ENTRYPOINT ["powershell"]