-- Align purchase table with Order entity mapping
ALTER TABLE purchase
    ADD COLUMN product_id BIGINT NOT NULL COMMENT '상품 ID',
    ADD COLUMN quantity INT NOT NULL COMMENT '주문 수량';

ALTER TABLE purchase
    ADD CONSTRAINT fk_purchase_product FOREIGN KEY (product_id) REFERENCES product(id);

CREATE INDEX idx_purchase_user_id ON purchase(user_id);
CREATE INDEX idx_purchase_product_id ON purchase(product_id);


