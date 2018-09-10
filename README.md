Postgres: createdat < NOW() - INTERVAL '5 minutes'
MSSQL: createdat < DATEADD(mi,-5,GETDATE())
H2: createdat < DATEADD('MINUTE',-5, CURRENT_TIMESTAMP)