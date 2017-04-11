 .\msbuild C:\src\Docker.AtSea\Docker.AtSea.Database\Docker.AtSea.Database.sqlproj `
    /p:SQLDBExtensionsRefPath="C:\Microsoft.Data.Tools.Msbuild.10.0.61026\lib\net40" `
    /p:SqlServerRedistPath="C:\Microsoft.Data.Tools.Msbuild.10.0.61026\lib\net40"; `
    cp -Recurse 'C:\src\Docker.AtSea\Docker.AtSea.Database\bin\Debug\*' 'c:\bin'