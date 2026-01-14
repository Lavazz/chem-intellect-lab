CREATE INDEX IF NOT EXISTS idx_reagent_stock_reagent_id ON reagent_stock (reagent_id);
CREATE INDEX IF NOT EXISTS idx_reagent_consumption_reagent_id ON reagent_consumption (reagent_id);
CREATE INDEX IF NOT EXISTS idx_reagent_consumption_experiment_id ON reagent_consumption (experiment_id);