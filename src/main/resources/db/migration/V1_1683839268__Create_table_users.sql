CREATE TABLE users (
   id BIGSERIAL PRIMARY KEY,
   first_name TEXT NOT NULL,
   last_name TEXT NOT NULL,
   username TEXT UNIQUE NOT NULL,
   password TEXT NOT NULL,
   created_at TIMESTAMPTZ DEFAULT NOW(),
   updated_at TIMESTAMPTZ DEFAULT NOW()
);