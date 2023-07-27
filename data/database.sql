BEGIN;

CREATE TABLE "users"(
"id" INTEGER GENERATED By DEFAULT AS IDENTITY PRIMARY KEY,
"email" TEXT NOT NULL UNIQUE,
"password" TEXT NOT NULL,
"is_account_active" BOOLEAN NOT NULL DEFAULT true,
"account_activation_at" TIMESTAMPTZ,
"maximum_account_activation_date" TIMESTAMPTZ NOT NULL DEFAULT now() + INTERVAL '7 days',
"last_login_at" TIMESTAMPTZ,
"created_at" TIMESTAMPTZ NOT NULL DEFAULT now(),
"updated_at" TIMESTAMPTZ
);

CREATE TABLE "accounts"(
"id" INTEGER GENERATED By DEFAULT AS IDENTITY PRIMARY KEY,
"password" TEXT NOT NULL,
"is_account_active" BOOLEAN NOT NULL DEFAULT true,
"account_activation_at" TIMESTAMPTZ,
"maximum_account_activation_date" TIMESTAMPTZ NOT NULL DEFAULT now() + INTERVAL '7 days',
"last_login_at" TIMESTAMPTZ,
"last_failed_login_at" TIMESTAMPTZ,
"user_id" INTEGER NOT NULL REFERENCES "users"("id") ON DELETE CASCADE,
"created_at" TIMESTAMPTZ NOT NULL DEFAULT now(),
"updated_at" TIMESTAMPTZ
);

CREATE TABLE "random_text_categories"(
"id" INTEGER GENERATED By DEFAULT AS IDENTITY PRIMARY KEY,
"category" TEXT NOT NULL,
"created_at" TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE TABLE "random_text_user"(
"id" INTEGER GENERATED By DEFAULT AS IDENTITY PRIMARY KEY,
"random_text" TEXT NOT NULL,
"iv" TEXT NOT NULL,
"expired_at" TIMESTAMPTZ NOT NULL,
"user_id" INTEGER NOT NULL REFERENCES "users"("id") ON DELETE CASCADE,
"random_text_id" INTEGER NOT NULL REFERENCES "random_text_categories"("id") ON DELETE CASCADE,
"created_at" TIMESTAMPTZ NOT NULL DEFAULT now(),
"updated_at" TIMESTAMPTZ
);

CREATE TABLE "products"(
"id" INTEGER GENERATED By DEFAULT AS IDENTITY PRIMARY KEY,
"path" TEXT NOT NULL UNIQUE,
"file_extension" TEXT NOT NULL,
"file_name" TEXT NOT NULL,
"product_end_date" TIMESTAMPTZ NOT NULL,
"product_open_date" TIMESTAMPTZ NOT NULL DEFAULT now(),
"user_id" INTEGER NOT NULL REFERENCES "users"("id") ON DELETE CASCADE;
"created_at" TIMESTAMPTZ NOT NULL DEFAULT now(),
"updated_at" TIMESTAMPTZ
);

CREATE TABLE "product_user"(
"id" INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
"user_id" INTEGER NOT NULL REFERENCES "users"("id") ON DELETE CASCADE,
"product_id" INTEGER NOT NULL  REFERENCES "products"("id") ON DELETE CASCADE,
"created_at" TIMESTAMPTZ NOT NULL DEFAULT now(),
"updated_at" TIMESTAMPTZ
);

CREATE TABLE "friends"(
"id" INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
"user_id" INTEGER NOT NULL REFERENCES "users"("id") ON DELETE CASCADE,
"friend_id" INTEGER NOT NULL REFERENCES "users"("id") ON DELETE CASCADE,
"nickname" TEXT,
"is_friend_request_new" BOOLEAN NOT NULL DEFAULT TRUE,
"is_friend_request_accepted" BOOLEAN NOT NULL DEFAULT FALSE,
"is_relation_accepted" BOOLEAN NOT NULL DEFAULT FALSE,
"is_relation_deleted" BOOLEAN NOT NULL DEFAULT FALSE,
"created_at" TIMESTAMPTZ NOT NULL DEFAULT now(),
"updated_at" TIMESTAMPTZ
);

CREATE TABLE "roles"(
"id" INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
"name" TEXT NOT NULL,
"created_at" TIMESTAMPTZ NOT NULL DEFAULT now(),
"updated_at" TIMESTAMPTZ
);

CREATE TABLE "role_user"(
"id" INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
"user_id" INTEGER NOT NULL REFERENCES "users"("id") ON DELETE CASCADE,
"role_id" INTEGER NOT NULL REFERENCES "roles"("id") ON DELETE CASCADE,
"created_at" TIMESTAMPTZ NOT NULL DEFAULT now(),
"updated_at" TIMESTAMPTZ
); 

CREATE TABLE "tokens"(
"id" INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
"user_id" INTEGER NOT NULL REFERENCES "users"("id") ON DELETE CASCADE,
"token" TEXT NOT NULL,
"is_valid" BOOLEAN NOT NULL DEFAULT FALSE,
"expired_at" TIMESTAMPTZ NOT NULL DEFAULT now(),
"created_at"TIMESTAMPTZ NOT NULL DEFAULT now(),
"updated_at" TIMESTAMPTZ
);

COMMIT;