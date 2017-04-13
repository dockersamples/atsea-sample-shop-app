CREATE TABLE [dbo].[Products]
(
	[ProductId] BIGINT NOT NULL PRIMARY KEY IDENTITY, 
    [Description] VARCHAR(MAX) NOT NULL, 
    [Image] VARBINARY(MAX) NULL, 
    [Price] NUMERIC(13, 2) NOT NULL, 
    [Name] VARCHAR(255) NOT NULL
)
