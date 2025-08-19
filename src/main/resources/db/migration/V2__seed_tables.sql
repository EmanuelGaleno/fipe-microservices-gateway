INSERT INTO brand (fipe_code, name, type)
VALUES ('59', 'VW - VolksWagen', 'CARROS')
ON CONFLICT DO NOTHING;

INSERT INTO vehicle (brand_id, fipe_model_code, model_name, type)
SELECT b.id, 5585, 'AMAROK CD2.0 16V TDI 4x2', 'CARROS'
FROM brand b WHERE b.fipe_code='59' AND b.type='CARROS'
ON CONFLICT DO NOTHING;

INSERT INTO vehicle (brand_id, fipe_model_code, model_name, type)
SELECT b.id, 8531, 'AMAROK Comfortline 2.0 TDI 4x4 Aut.', 'CARROS'
FROM brand b WHERE b.fipe_code='59' AND b.type='CARROS'
ON CONFLICT DO NOTHING;
