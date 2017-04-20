$nuGetPath = "C:\Chocolatey\bin\nuget.bat"
$msBuildPath = "C:\Program Files (x86)\MSBuild\14.0\Bin\MSBuild.exe"

cd c:\src\Docker.AtSea
& $nuGetPath restore .\Docker.AtSea.sln
& $msBuildPath .\Docker.AtSea.Api\Docker.AtSea.Api.csproj /p:OutputPath=c:\bin `
               /p:DeployOnBuild=true /p:VSToolsPath=C:\MSBuild.Microsoft.VisualStudio.Web.targets.14.0.0.3\tools\VSToolsPath
